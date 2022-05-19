package com.FurnitureKing.Project.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDateTime {

    public static Date getCurrentDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println(formatter.format(date));
        return date;
    }
}
