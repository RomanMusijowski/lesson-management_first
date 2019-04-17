package com.roman_musijowski.pgs_lessons.services.security;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EncryptionServiceImpl implements EncryptionService {

    private StrongPasswordEncryptor strongEncryptor;

    @Autowired
    public EncryptionServiceImpl(StrongPasswordEncryptor strongEncryptor) {
        this.strongEncryptor = strongEncryptor;
    }

    public String encryptString(String input){

        System.out.println("Input string "+input);
        return strongEncryptor.encryptPassword(input);
    }

    public boolean checkPassword(String plainPassword, String encryptedPassword){
        System.out.println("Plain password"+plainPassword);
        System.out.println("Encrypted password"+encryptedPassword);
        return strongEncryptor.checkPassword(plainPassword, encryptedPassword);
    }

}
