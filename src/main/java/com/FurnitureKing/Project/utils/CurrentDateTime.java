package com.FurnitureKing.Project.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDateTime {

    public static Date getCurrentDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        //ici le formatter qui galère mais tranquille plus tard
        return date;
    }
}
