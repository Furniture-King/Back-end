package com.FurnitureKing.Project.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Password {

    public static String[] Hash(String psw){
        String salt = BCrypt.gensalt(12);
        String pswHashed = BCrypt.hashpw(psw,salt);
        return new String[] {pswHashed,salt};
    }

    public static Boolean Check(String psw, String salt){
        Boolean bool = BCrypt.checkpw(psw,salt);
        return bool;
    }

}
