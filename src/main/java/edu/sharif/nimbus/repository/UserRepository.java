package edu.sharif.nimbus.repository;

import edu.sharif.nimbus.exception.UserNameNotAllowedException;
import edu.sharif.nimbus.exception.UserNotActiveException;
import edu.sharif.nimbus.exception.UserNotAllowedException;
import edu.sharif.nimbus.exception.UserNotFoundException;
import edu.sharif.nimbus.exception.UserUnAuthorizedException;
import edu.sharif.nimbus.model.Token;
import edu.sharif.nimbus.model.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    @Getter
    private static final ArrayList<User> userList = new ArrayList<>();

    private boolean isUsernameUnique(String username) {
        return getUserByUsername(username) == null;
    }

    private User getUserByUsername(String username) {
        for (User user : userList) {
            if (username.equals(user.getUsername())) {
                return user;
            }
        }
        return null;
    }

    private User getUserByToken(String token) {
        for (User user : userList) {
            if (user.getTokens().stream().anyMatch(t -> t.getValue().equals(token)) || user.getMainToken().getValue().equals(token)) {
                return user;
            }
        }
        return null;
    }

    private boolean tokenIsExpired(String authorization, User user) {
        if (user.getMainToken().getValue().equals(authorization)) {
            return false;
        }
        LocalDateTime expireDate = user.getTokens().stream().filter(token -> token.getValue().equals(authorization)).toList().get(0).getExpirationDate();
        return expireDate.isBefore(LocalDateTime.now());
    }

    public boolean registerUser(String username, String password) {
        if (!isUsernameUnique(username)) {
            throw new UserNameNotAllowedException(username);
        }
        userList.add(new User(username, password));
        return true;
    }

    public User loginUser(String username, String password) {
        User user = getUserByUsername(username);
        if (user == null) {
            throw new UserNotFoundException(username);
        }
        if (!user.isActive()) {
            throw new UserNotActiveException(username + " is not active!");
        }
        if (!user.getPassword().equals(password)) {
            throw new UserUnAuthorizedException("username or password is incorrect!");
        }
        return user;
    }

    public boolean changeUserActivation(String username, boolean setActive) {
        User user = getUserByUsername(username);
        if (user == null) {
            throw new UserNotFoundException(username);
        }
        user.changeActivation(setActive);
        return true;

    }

    public User authorizeUser(String authorization) {
        User user = getUserByToken(authorization);
        if (user == null) {
            throw new UserUnAuthorizedException("unauthorized!");
        }
        if (tokenIsExpired(authorization, user)) {
            throw new UserUnAuthorizedException("Token is expired!");
        }
        return user;
    }

    public Token addToken(String authorization, String name, String expirationDate) {
        User user = authorizeUser(authorization);
        return user.addToken(name, expirationDate);
    }

    public void deleteToken(String authorization) {
        User user = authorizeUser(authorization);
        if (user.getMainToken().getValue().equals(authorization)) {
            throw new UserNotAllowedException("Not allowed to delete default token!");
        }
        user.deleteToken(authorization);
    }

    public List<Token> getTokens(String authorization) {
        User user = authorizeUser(authorization);
        return user.getTokens();
    }
}
