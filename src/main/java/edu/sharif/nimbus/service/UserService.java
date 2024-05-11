package edu.sharif.nimbus.service;

import edu.sharif.nimbus.exception.UserNotAllowedException;
import edu.sharif.nimbus.model.Token;
import edu.sharif.nimbus.model.User;
import edu.sharif.nimbus.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;


@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final String adminApiKey;

    public ArrayList<User> getAllUsers(String authorization) {
        authorizeAdmin(authorization);
        return UserRepository.getUserList();
    }

    public boolean registerUser(String username, String password) {
        return userRepository.registerUser(username, password);
    }

    public boolean changeUserActivation(String authorization, String username, boolean activation) {
        authorizeAdmin(authorization);
        return userRepository.changeUserActivation(username, activation);
    }

    public Token loginUser(String username, String password) {
        return userRepository.loginUser(username, password).getMainToken();
    }

    private void authorizeAdmin(String authorization) throws UserNotAllowedException {
        if (!authorization.equals(this.adminApiKey)) {
            throw new UserNotAllowedException("Not Allowed!");
        }
    }

}
