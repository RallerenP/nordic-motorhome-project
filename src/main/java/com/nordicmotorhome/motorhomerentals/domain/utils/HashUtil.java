package com.nordicmotorhome.motorhomerentals.domain.utils;

import org.mindrot.jbcrypt.BCrypt;
//Author : RAP
public class HashUtil {
    private static final int rounds = 10;

    public static String hash(String pw) {
        return BCrypt.hashpw(pw, BCrypt.gensalt(rounds));
    }

    public static boolean verify(String pw, String hash) {
        return BCrypt.checkpw(pw, hash);
    }
}
