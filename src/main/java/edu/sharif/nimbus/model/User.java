package edu.sharif.nimbus.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@Data
@RequiredArgsConstructor
public class User {
    private final String username;
    private final String password;

    private final ArrayList<Token> tokens = new ArrayList<>();
    private boolean isActive = false;



}
