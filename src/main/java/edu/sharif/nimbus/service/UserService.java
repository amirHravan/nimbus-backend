package edu.sharif.nimbus.service;

import edu.sharif.nimbus.model.User;
import edu.sharif.nimbus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;


@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Value("admin.password.key")
    private String adminApiKey;

    public ArrayList<User> getAllUsers(String authorization) {
        return UserRepository.getUserList();
    }

    public boolean registerUser(User user) {
        return userRepository.registerUser(user);
    }

    public boolean changeUserActivation(String username, boolean activation) {
        return userRepository.changeUserActivation(username, activation);
    }

    public boolean loginUser(User user) {
        return userRepository.loginUser(user);
    }

}
