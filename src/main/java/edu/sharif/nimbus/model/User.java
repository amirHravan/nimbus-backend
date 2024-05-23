package edu.sharif.nimbus.model;


import edu.sharif.nimbus.util.TokenGenerator;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class User {
    @Id
    private final String username;
    private final String password;
    @OneToMany(cascade = CascadeType.ALL)
    private final List<Token> tokens = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
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
                LocalDateTime.MAX);
    }

    private void deactivateUser() {
        this.isActive = false;
        this.mainToken = null;
        this.tokens.clear();
    }

    public Token addToken(String name, String expirationDate) {
        Token newToken = new Token(name, TokenGenerator.generateApiToken(), LocalDateTime.parse(expirationDate));
        this.tokens.add(newToken);
        return newToken;
    }

    public void deleteToken(String authorization) {
        this.tokens.removeIf(token -> token.getValue().equals(authorization));
    }

}
