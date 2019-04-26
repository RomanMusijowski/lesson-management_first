package com.roman_musijowski.pgs_lessons.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class EncryptionServiceImpl implements EncryptionService {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public EncryptionServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encryptString(String input) {
        System.out.println("Input password - "+input);
        return passwordEncoder.encode(input);
    }

    @Override
    public boolean checkPassword(String plainPassword, String encryptedPassword) {
        System.out.println("Check new password - "+plainPassword);
        System.out.println("In db password - "+encryptedPassword);
        return passwordEncoder.matches(plainPassword, encryptedPassword);
    }
}
