package com.starglass.api.utils;

import java.util.List;

public class StringHelper {

    private StringHelper() {
    }

    public static String removeCharacter(String text, String character) {
        return text.replace(character, "");
    }

    public static String removeCharacters(String text, List<String> characters) {
        for (String character : characters) {
            text = text.replace(character, "");
        }
        return text;
    }

}
