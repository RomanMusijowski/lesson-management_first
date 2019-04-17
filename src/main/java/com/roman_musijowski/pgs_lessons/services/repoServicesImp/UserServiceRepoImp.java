package com.roman_musijowski.pgs_lessons.services.repoServicesImp;

import com.roman_musijowski.pgs_lessons.commands.UserForm;
import com.roman_musijowski.pgs_lessons.converters.UserFormToUser;
import com.roman_musijowski.pgs_lessons.models.User;
import com.roman_musijowski.pgs_lessons.repositories.UserRepository;
import com.roman_musijowski.pgs_lessons.services.UserService;
import com.roman_musijowski.pgs_lessons.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceRepoImp implements UserService {

    private UserRepository userRepository;
    private UserFormToUser userFormToUser;
    private EncryptionService encryptionService;

    @Autowired
    public UserServiceRepoImp(UserRepository userRepository, UserFormToUser userFormToUser, EncryptionService encryptionService) {
        this.userRepository = userRepository;
        this.userFormToUser = userFormToUser;
        this.encryptionService = encryptionService;
    }

    @Override
    public List<User> listAll() {
        List<User> users = new  ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User getById(Long id) {
        Optional<User> student = userRepository.findById(id);
        return student.get();
    }

    @Override
    public User saveOrUpdate(User object) {
//        System.out.println("Save or update - "+object.toString());
        if(object.getPassword() != null){
            object.setEncryptedPassword(encryptionService.encryptString(object.getPassword()));
        }

//        System.out.println("Save or update(encrypt) - "+object.toString());
        return userRepository.save(object);
    }

    @Override
    public void delete(User object) {
        userRepository.delete(object);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getById(Integer id) {
        return null;
    }

    @Override
    public User saveOrUpdateUserForm(UserForm userForm) {

        User newUser = userFormToUser.convert(userForm);

        if (newUser.getId() != null) {
            User existingUser = getById(newUser.getId());

            newUser.setEnabled(existingUser.getEnabled());
        }
        return saveOrUpdate(newUser);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }


//    @Override
//    public User findByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }

}
