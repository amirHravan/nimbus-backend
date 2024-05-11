package edu.sharif.nimbus.exception;

public class UserUnAuthorizedException extends RuntimeException {
    public UserUnAuthorizedException(String message) {
        super(message);
    }
}
