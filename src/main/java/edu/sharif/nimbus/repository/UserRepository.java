package edu.sharif.nimbus.repository;

import edu.sharif.nimbus.exception.UserNotFoundException;
import edu.sharif.nimbus.model.User;
import lombok.Getter;

import java.util.ArrayList;

public class UserRepository {

    @Getter
    private final static ArrayList<User> userList = new ArrayList<>();

    private boolean isUsernameUnique(String username) {
        return getUserByUserName(username) == null;
    }

    private User getUserByUserName(String username) {
        for (User user : userList) {
            if (username.equals(user.getUsername())) {
                return user;
            }
        }
        return null;
    }

    public boolean registerUser(User user) {
        if (!isUsernameUnique(user.getUsername())) {
            return false;
        }
        userList.add(user);
        return true;
    }

    public boolean loginUser(User user) {
        if (isUsernameUnique(user.getUsername())) {
            throw new UserNotFoundException(user.getUsername());
        }

        return true;
    }

    public boolean changeUserActivation(String username, boolean setActive) {
        if (isUsernameUnique(username)) {
            throw new UserNotFoundException(username);
        }

        User user = getUserByUserName(username);
        assert user != null;
        user.setActive(setActive);
        return true;

    }

}
