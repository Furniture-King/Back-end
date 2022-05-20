package com.FurnitureKing.Project.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Password {

    public static String[] Hash(String psw){
        String[] data = new String[2];
        String salt = BCrypt.gensalt(12);
        data[0] = salt; //le hash
        String pswHashed = BCrypt.hashpw(psw,salt);
        data[1] = pswHashed; //le salt
        return data;

    }

    public static Boolean Check(String psw, String hash, String salt){
        Boolean bool = BCrypt.checkpw(psw,salt);
        System.out.println(bool);
        return bool;
}

}
