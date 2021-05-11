/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;


import java.security.MessageDigest;
/**
 *
 * @author Arnulfo
 */
public class HexDigest {
            public static String hexDigest(String message) {
        MessageDigest md;

        byte[] buffer, digest;
        String hash = "";

        try {
            buffer = message.getBytes("UTF-8");
            md = MessageDigest.getInstance("SHA1");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        md.update(buffer);
        digest = md.digest();

        for (byte aux : digest) {
            int b = aux & 0xff;

            String s = Integer.toHexString(b);

            if (s.length() == 1) {
                hash += "0";
            }
            hash += s;
        }
        return hash;
    }
}
