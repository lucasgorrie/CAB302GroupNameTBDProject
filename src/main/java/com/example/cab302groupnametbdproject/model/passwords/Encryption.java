package com.example.cab302groupnametbdproject.model.passwords;

import java.nio.charset.StandardCharsets;
import java.util.HexFormat;

public class Encryption {
    public static String encrypt(String plainText, String key) {
        byte[] plainTextBytes = plainText.getBytes(StandardCharsets.UTF_8);
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        String finalHexString = "";
        for (int i = 0; i < plainTextBytes.length; i++) {
            String textByteBinaryString = String.format("%8s", Integer.toBinaryString(plainTextBytes[i] & 0xff))
                    .replace(" ", "0");
            String keyByteBinaryString = String.format("%8s", Integer.toBinaryString(keyBytes[i % keyBytes.length] & 0xff))
                    .replace(" ", "0");
            String encryptedBinaryString = "";
            for (int in = 0; in < 8; in++) {
                if (textByteBinaryString.charAt(in) == keyByteBinaryString.charAt(in)) {
                    encryptedBinaryString += 0;
                } else { encryptedBinaryString += 1; }
            }
            byte testguy = (byte) Integer.parseInt(encryptedBinaryString, 2);
            String hexedNumber = String.format("%02X", testguy);
            finalHexString += hexedNumber;


        }
        return finalHexString;
    }

    public static String decrypt(String encryptedHex, String key) {
        byte[] encrypedByteArray = HexFormat.of().parseHex(encryptedHex);
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        String unencyrptedHexString = "";
        for (int i = 0; i < encrypedByteArray.length; i++) {
            String encryptedByteBinaryString = String.format("%8s", Integer.toBinaryString(encrypedByteArray[i] & 0xff))
                    .replace(" ", "0");
            String keyByteBinaryString = String.format("%8s", Integer.toBinaryString(keyBytes[i % keyBytes.length] & 0xff))
                    .replace(" ", "0");
            String encryptedBinaryString = "";
            for (int in = 0; in < 8; in++) {
                if (encryptedByteBinaryString.charAt(in) == keyByteBinaryString.charAt(in)) {
                    encryptedBinaryString += 0;
                } else { encryptedBinaryString += 1; }
            }
            byte testguy = (byte) Integer.parseInt(encryptedBinaryString, 2);
            String hexedNumber = String.format("%02X", testguy);
            unencyrptedHexString += hexedNumber;


        }
        byte[] unencryptedByteArray = HexFormat.of().parseHex(unencyrptedHexString);
        return new String(unencryptedByteArray, StandardCharsets.UTF_8);
    }


}
