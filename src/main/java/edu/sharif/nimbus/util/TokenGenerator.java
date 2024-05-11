package edu.sharif.nimbus.util;

import java.util.Random;

public class TokenGenerator {
    private static final String TOKEN_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final int TOKEN_LENGTH = 18;

    public static String generateApiToken() {
        StringBuilder result = new StringBuilder("API ");
        Random rnd = new Random();
        while (result.length() < TOKEN_LENGTH) {
            int index = (int) (rnd.nextFloat() * TOKEN_CHARACTERS.length());
            result.append(TOKEN_CHARACTERS.charAt(index));
        }
        return result.toString();
    }
}
