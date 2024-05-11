package edu.sharif.nimbus.model;


import edu.sharif.nimbus.util.TokenGenerator;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@Data
@RequiredArgsConstructor
public class User {
    private final String username;
    private final String password;
    private final ArrayList<Token> tokens = new ArrayList<>();
    private Token mainToken = null;
    private boolean isActive = false;

    public void changeActivation(Boolean setActive) {
        if (setActive) ActivateUser();
        else DeactivateUser();
    }

    private void ActivateUser() {
        this.isActive = true;
        this.mainToken = new Token(
                "main_token",
                TokenGenerator.generateApiToken(),
                null);
    }

    private void DeactivateUser() {
        this.isActive = false;
        this.mainToken = null;
    }


}
