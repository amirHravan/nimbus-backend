package edu.sharif.nimbus.service;

import edu.sharif.nimbus.exception.*;
import edu.sharif.nimbus.model.Token;
import edu.sharif.nimbus.model.User;
import edu.sharif.nimbus.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final String adminApiKey;

    public List<User> getAllUsers(String authorization) {
        authorizeAdmin(authorization);
        return userRepository.findAll();
    }

    public boolean registerUser(String username, String password) {
        if (userRepository.findById(username).isPresent()) {
            throw new UserNameNotAllowedException(username);
        }
        userRepository.save(new User(username, password));
        return true;
    }

    public boolean changeUserActivation(String authorization, String username, boolean setActive) {
        authorizeAdmin(authorization);
        Optional<User> user = userRepository.findById(username);
        if (user.isEmpty()) {
            throw new UserNotFoundException(username);
        }
        user.get().changeActivation(setActive);
        userRepository.save(user.get());
        return true;
    }

    public Token loginUser(String username, String password) {
        Optional<User> user = userRepository.findById(username);
        if (user.isEmpty()) {
            throw new UserNotFoundException(username);
        }
        if (!user.get().isActive()) {
            throw new UserNotActiveException(username + " is not active!");
        }
        if (!user.get().getPassword().equals(password)) {
            throw new UserUnAuthorizedException("username or password is incorrect!");
        }
        return user.get().getMainToken();
    }

    private void authorizeAdmin(String authorization) throws UserNotAllowedException {
        if (!authorization.equals(this.adminApiKey)) {
            throw new UserNotAllowedException("Not Allowed!");
        }
    }

    public Token addToken(String authorization, String name, String expirationDate) {
        User user = authorizeUser(authorization);
        Token newToken = user.addToken(name, expirationDate);
        userRepository.save(user);
        return newToken;
    }

    public void deleteToken(String authorization) {
        User user = authorizeUser(authorization);
        if (user.getMainToken().getValue().equals(authorization)) {
            throw new UserNotAllowedException("Not allowed to delete default token!");
        }
        user.deleteToken(authorization);
        userRepository.save(user);
    }

    public List<Token> getTokens(String authorization) {
        User user = authorizeUser(authorization);
        return user.getTokens();
    }

    private Optional<User> findUserByToken(String tokenValue) {
        for (User user : userRepository.findAll()) {
            if (user.getTokens().stream().anyMatch(token -> token.getValue().equals(tokenValue)) || user.getMainToken().getValue().equals(tokenValue)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    private boolean tokenIsExpired(String authorization, User user) {
        if (user.getMainToken().getValue().equals(authorization)) {
            return false;
        }
        LocalDateTime expireDate = user.getTokens().stream().filter(token -> token.getValue().equals(authorization)).toList().get(0).getExpirationDate();
        return expireDate.isBefore(LocalDateTime.now());
    }

    public User authorizeUser(String authorization) {
        Optional<User> user = findUserByToken(authorization);
        if (user.isEmpty()) {
            throw new UserUnAuthorizedException("unauthorized!");
        }
        if (tokenIsExpired(authorization, user.get())) {
            throw new UserUnAuthorizedException("Token is expired!");
        }
        if (!user.get().isActive()) {
            throw new UserNotActiveException("User is not active!");
        }
        return user.get();
    }

}
