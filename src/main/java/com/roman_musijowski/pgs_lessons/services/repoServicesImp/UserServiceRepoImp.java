package com.roman_musijowski.pgs_lessons.services.repoServicesImp;

import com.roman_musijowski.pgs_lessons.commands.UserForm;
import com.roman_musijowski.pgs_lessons.converters.UserFormToUser;
import com.roman_musijowski.pgs_lessons.models.User;
import com.roman_musijowski.pgs_lessons.models.security.Role;
import com.roman_musijowski.pgs_lessons.repositories.UserRepositoryImp;
import com.roman_musijowski.pgs_lessons.security.services.EncryptionService;
import com.roman_musijowski.pgs_lessons.services.RoleSevice;
import com.roman_musijowski.pgs_lessons.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceRepoImp implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceRepoImp.class);


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
    @Cacheable(value = "users")
    public List<User> listAll() {
        logger.info("Get list of users");

        List<User> users = new  ArrayList<>();
        userRepositoryImp.findAll().forEach(users::add);
        return users;
    }

    @Override
    @Cacheable(cacheNames = "users", key = "#id")
    public User getById(Long id) {
        logger.info("Get user - " + id);
        return userRepositoryImp.getOne(id);
    }

    @Override
    @Caching(evict =
            {@CacheEvict(cacheNames = "lessons", allEntries = true),
            @CacheEvict(cacheNames = "users", allEntries = true)})
    public User saveOrUpdate(User object) {
        logger.info("Save user - " + object.toString());

        if(object.getPassword() != null){
            logger.info("User if != null. Updating user.");
            object.setEncryptedPassword(encryptionService.encryptString(object.getPassword()));
        }

        return userRepositoryImp.save(object);
    }

    @Override
    @CacheEvict(cacheNames = "users", allEntries = true)
    public User saveOrUpdateUserForm(UserForm userForm) {
        logger.info("Save userForm - " + userForm);

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
    @CacheEvict(cacheNames = "users", key = "#id")
    public User deleteById(Long id) {

        User user= getById(id);

        if (id == 1L){
            logger.warn("User id = " + id + ". You can't deleteById admin");
            return null;
        }
        userRepositoryImp.deleteById(id);
        logger.info("Deleted user - " + id);
        return user;
    }

    @Override
    @Cacheable(cacheNames = "users", key = "#userName")
    public User findByUserName(String userName) {
        logger.info("Find by userName user - " + userName);

        User user = userRepositoryImp.findByUserName(userName);
        return user;
    }


//    @Override
//    public User findByEmail(String email) {
//        return userRepositoryImp.findByEmail(email);
//    }

}
