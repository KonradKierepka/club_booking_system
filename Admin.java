package Projekt;

import java.util.Random;

import javax.management.monitor.StringMonitorMBean;

public class Admin extends Pracownik{

    private String password;
    private String key;

    public Admin(String imie, String nazwisko, String adres, String nr_dowodu, Integer nr_telefonu, String password) {
        super(imie, nazwisko, adres, nr_dowodu, nr_telefonu);
        this.key = generateKey(password);
        this.password = encryptPassword(password, key);
    }
    
    private String encryptPassword(String password, String key) {
        String encrypted = new String("");
        for(byte i = 0; i < password.length(); i++) {
            encrypted = encrypted + (password.charAt(i) ^ key.charAt(i)) + " ";
        }
        
        System.out.println(encrypted);

        return encrypted;
    }

    private String decryptPassword(String password, String key) {
        String decrypted = new String("");
        String[] passwordWords = password.split(" "); 
        for(byte i = 0; i < key.length(); i++) {
            decrypted = decrypted + (char)(Integer.valueOf(passwordWords[i]) ^ key.charAt(i));
        }

        return decrypted;
    }

    /*
        Generate Key for xor encryption algorythm.
    */

    private String generateKey(String password) {
        String key = new String("");
        Random rand = new Random();
        for(byte i = 0; i<password.length(); i++) {
            key = key + (char)(rand.nextInt((126 - 32) + 1) + 32);
        }

        System.out.println(key);

        return key;
    }

    public String getPassword() {
        String pass = decryptPassword(password, key);
        return pass;
    }
}
