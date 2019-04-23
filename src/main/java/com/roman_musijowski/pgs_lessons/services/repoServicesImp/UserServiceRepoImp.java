package com.roman_musijowski.pgs_lessons.services.repoServicesImp;

import com.roman_musijowski.pgs_lessons.commands.UserForm;
import com.roman_musijowski.pgs_lessons.converters.UserFormToUser;
import com.roman_musijowski.pgs_lessons.models.User;
import com.roman_musijowski.pgs_lessons.models.security.Role;
import com.roman_musijowski.pgs_lessons.repositories.UserRepository;
import com.roman_musijowski.pgs_lessons.services.RoleSevice;
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
    private RoleSevice roleSevice;

    @Autowired
    public UserServiceRepoImp(UserRepository userRepository,  RoleSevice roleSevice, UserFormToUser userFormToUser, EncryptionService encryptionService) {
        this.userRepository = userRepository;
        this.userFormToUser = userFormToUser;
        this.encryptionService = encryptionService;
        this.roleSevice = roleSevice;
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
        if(object.getPassword() != null){
            System.out.println("Save or update User");
            object.setEncryptedPassword(encryptionService.encryptString(object.getPassword()));
        }
        System.out.println("User updated");

        return userRepository.save(object);
    }

    @Override
    public void delete(User object) {
        userRepository.delete(object);
    }

    @Override
    public void delete(Long id) {

        if (getById(id).getUserName() == "admin@gmail.com"){
            System.out.println("You can't delete admin");
            return;
        }
        userRepository.deleteById(id);
    }

    @Override
    public User getById(Integer id) {
        return null;
    }

    @Override
    public User saveOrUpdateUserForm(UserForm userForm) {
        List<Role> roles = (List<Role>) roleSevice.listAll();

        User newUser = userFormToUser.convert(userForm);


        if (newUser.getId() == null) {

            roles.forEach(role -> {
                if (role.getRole() == "STUDENT") {
                    System.out.println("add new role student");
                    newUser.addRole(role);
                }
            });
        }else {
            System.out.println("Existing user ! ");
            User existingUser = getById(newUser.getId());

            newUser.setEnabled(existingUser.getEnabled());
            newUser.setRoles(existingUser.getRoles());
            newUser.setLessons(existingUser.getLessons());

//            System.out.println("Roles - "+existingUser.getRoles());
//            System.out.println("Lessons - "+existingUser.getLessons());
            System.out.println("Added info to existing user!!");
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
