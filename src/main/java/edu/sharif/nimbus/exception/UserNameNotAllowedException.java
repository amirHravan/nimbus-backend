package edu.sharif.nimbus.exception;

public class UserNameNotAllowedException extends RuntimeException {
    public UserNameNotAllowedException(String username) {
        super(username + " is not allowed.");
    }
}
