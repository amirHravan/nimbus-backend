package edu.sharif.nimbus.model;


import edu.sharif.nimbus.util.TokenGenerator;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@Data
@RequiredArgsConstructor
public class User {
    private final String username;
    private final String password;
    private final ArrayList<Token> tokens = new ArrayList<>();
    private Token mainToken = null;
    private boolean isActive = false;

    public void changeActivation(boolean setActive) {
        if (setActive) activateUser();
        else deactivateUser();
    }

    private void activateUser() {
        this.isActive = true;
        this.mainToken = new Token(
                "main_token",
                TokenGenerator.generateApiToken(),
                null);
    }

    private void deactivateUser() {
        this.isActive = false;
        this.mainToken = null;
    }

    public Token addToken(String name, Long expirationDate) {
        Token newToken = new Token(name, TokenGenerator.generateApiToken(), expirationDate);
        this.tokens.add(newToken);
        return newToken;
    }

    public void deleteToken(String authorization) {
        this.tokens.removeIf(token -> token.getValue().equals(authorization));
    }


}
