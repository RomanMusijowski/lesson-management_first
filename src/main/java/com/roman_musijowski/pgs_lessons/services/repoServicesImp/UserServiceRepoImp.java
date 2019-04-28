package com.roman_musijowski.pgs_lessons.services.repoServicesImp;

import com.roman_musijowski.pgs_lessons.commands.UserForm;
import com.roman_musijowski.pgs_lessons.converters.UserFormToUser;
import com.roman_musijowski.pgs_lessons.models.User;
import com.roman_musijowski.pgs_lessons.models.security.Role;
import com.roman_musijowski.pgs_lessons.repositories.UserRepositoryImp;
import com.roman_musijowski.pgs_lessons.security.services.EncryptionService;
import com.roman_musijowski.pgs_lessons.services.RoleSevice;
import com.roman_musijowski.pgs_lessons.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceRepoImp implements UserService {

    private UserRepositoryImp userRepositoryImp;
    private UserFormToUser userFormToUser;
    private EncryptionService encryptionService;
    private RoleSevice roleSevice;

    @Autowired
    public UserServiceRepoImp(UserRepositoryImp userRepositoryImp, RoleSevice roleSevice, UserFormToUser userFormToUser, EncryptionService encryptionService) {
        this.userRepositoryImp = userRepositoryImp;
        this.userFormToUser = userFormToUser;
        this.encryptionService = encryptionService;
        this.roleSevice = roleSevice;
    }

    @Override
    public List<User> listAll() {
        List<User> users = new  ArrayList<>();
        userRepositoryImp.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User getById(Long id) {
        return userRepositoryImp.getOne(id);
    }

    @Override
    public User saveOrUpdate(User object) {
        if(object.getPassword() != null){
            System.out.println("Save or update User");
            object.setEncryptedPassword(encryptionService.encryptString(object.getPassword()));
        }
        System.out.println("User updated");

        return userRepositoryImp.save(object);
    }

    @Override
    public void deleteById(Long id) {

        if (id == 1L){
            System.out.println("You can't deleteById admin");
            return;
        }
        userRepositoryImp.deleteById(id);
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

            System.out.println("Added info to existing user!!");
        }

        return saveOrUpdate(newUser);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepositoryImp.findByUserName(userName);
    }


//    @Override
//    public User findByEmail(String email) {
//        return userRepositoryImp.findByEmail(email);
//    }

}
