package com.spring.springSecurity.Encrypt;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class AES {
    SecretKey secretKey;

    public void init() throws NoSuchAlgorithmException {
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(128);
        secretKey = generator.generateKey();
    }

    public String encrypt(String message) throws NoSuchPaddingException, NoSuchAlgorithmException {
        byte[] messByte = message.getBytes();
        Cipher encryptCiper = Cipher.getInstance("");
        return "";
    }
}
