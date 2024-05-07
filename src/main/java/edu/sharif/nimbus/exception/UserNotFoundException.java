package edu.sharif.nimbus.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String userName) {
        super(userName + "could not be found.");
    }
}
