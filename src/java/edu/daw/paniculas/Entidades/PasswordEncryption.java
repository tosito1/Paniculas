/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.daw.paniculas.Entidades;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class PasswordEncryption {
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;

    public static String encryptPassword(String password) {
        try {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = skf.generateSecret(spec).getEncoded();

            // Concatena la sal con el hash para almacenar ambos
            byte[] hashWithSalt = new byte[hash.length + salt.length];
            System.arraycopy(hash, 0, hashWithSalt, 0, hash.length);
            System.arraycopy(salt, 0, hashWithSalt, hash.length, salt.length);

            return Base64.getEncoder().encodeToString(hashWithSalt);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean verifyPassword(String enteredPassword, String storedPassword) {
        try {
            byte[] hashWithSalt = Base64.getDecoder().decode(storedPassword);
            byte[] salt = new byte[16];
            byte[] hash = new byte[hashWithSalt.length - salt.length];

            System.arraycopy(hashWithSalt, 0, hash, 0, hash.length);
            System.arraycopy(hashWithSalt, hash.length, salt, 0, salt.length);

            PBEKeySpec spec = new PBEKeySpec(enteredPassword.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] testHash = skf.generateSecret(spec).getEncoded();

            // Compara los hashes para verificar la contraseña
            int diff = hash.length ^ testHash.length;
            for (int i = 0; i < hash.length && i < testHash.length; i++) {
                diff |= hash[i] ^ testHash[i];
            }
            return diff == 0;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return false;
        }
    }
}

