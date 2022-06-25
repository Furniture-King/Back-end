package com.FurnitureKing.Project.utils;

import java.text.Normalizer;

public class DataFormat {

    public static String FormatString(String str) {
        String test = Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[\u0300-\u036F]", "");
        String firstLetter = test.substring(0, 1).toUpperCase();
        String restLetters = test.substring(1).toLowerCase();
        test = firstLetter + restLetters;
        test = test.replaceAll("\\s+", "");
        return test;
    }
}
