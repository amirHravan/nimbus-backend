package edu.sharif.nimbus.controller;

import edu.sharif.nimbus.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NimbusControllerAdvice {
    @ExceptionHandler(value = UserNotAllowedException.class)
    public ResponseEntity<Object> exception(UserNotAllowedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> exception(UserNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserUnAuthorizedException.class)
    public ResponseEntity<Object> exception(UserUnAuthorizedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = UserNotActiveException.class)
    public ResponseEntity<Object> exception(UserNotActiveException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = UserNameNotAllowedException.class)
    public ResponseEntity<Object> exception(UserNameNotAllowedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

}
