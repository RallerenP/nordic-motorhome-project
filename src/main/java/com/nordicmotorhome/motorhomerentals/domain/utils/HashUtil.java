package com.nordicmotorhome.motorhomerentals.domain.utils;

import org.mindrot.jbcrypt.BCrypt;

public class HashUtil {
    private final int rounds = 5;

    public static String hash(String pw) {
        return BCrypt.hashpw(pw, BCrypt.gensalt(5));
    }

    public static boolean verify(String pw, String hash) {
        return BCrypt.checkpw(pw, hash);
    }
}
