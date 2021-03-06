package com.nordicmotorhome.motorhomerentals.domain.utils;

import org.mindrot.jbcrypt.BCrypt;

//Author : RAP
//Utility class used for encrypting passwords, and verifying passwords, using BCrypt
public class HashUtil {
    private static final int rounds = 10;

    public static String hash(String pw) {
        return BCrypt.hashpw(pw, BCrypt.gensalt(rounds));
    }

    public static boolean verify(String pw, String hash) {
        return BCrypt.checkpw(pw, hash);
    }
}
