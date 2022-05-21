package com.FurnitureKing.Project.utils;

public class DataFormat {

    public static String FormatString(String str){

        String firstLetter = str.substring(0,1).toUpperCase();
        String restLetters = str.substring(1).toLowerCase();
        str = firstLetter + restLetters;
        str = str.replaceAll("\\s+","");
        return str;
    }
}
