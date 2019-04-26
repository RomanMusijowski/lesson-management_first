package com.roman_musijowski.pgs_lessons.security.services;

public interface EncryptionService {
    String encryptString(String input);
    boolean checkPassword(String plainPassword, String encryptedPassword);
}
