package edu.sharif.nimbus.repository;

import edu.sharif.nimbus.exception.UserNameNotAllowedException;
import edu.sharif.nimbus.exception.UserNotActiveException;
import edu.sharif.nimbus.exception.UserNotFoundException;
import edu.sharif.nimbus.exception.UserUnAuthorizedException;
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

    public boolean registerUser(String username, String password) {
        if (!isUsernameUnique(username)) {
            throw new UserNameNotAllowedException(username);
        }
        userList.add(new User(username, password));
        return true;
    }

    public User loginUser(String username, String password) {
        User user = getUserByUserName(username);
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
        User user = getUserByUserName(username);
        if (user == null) {
            throw new UserNotFoundException(username);
        }
        user.changeActivation(setActive);
        return true;

    }

}
