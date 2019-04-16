package com.roman_musijowski.pgs_lessons.bootstrap;

import com.roman_musijowski.pgs_lessons.models.Lesson;
import com.roman_musijowski.pgs_lessons.models.User;
import com.roman_musijowski.pgs_lessons.models.security.Role;
import com.roman_musijowski.pgs_lessons.repositories.LessonRepository;
import com.roman_musijowski.pgs_lessons.repositories.RoleRepository;
import com.roman_musijowski.pgs_lessons.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

//    private UserRepository userRepository;
//    private LessonRepository lessonRepository;
//    private RoleRepository roleRepository;
//
//    @Autowired
//    public DevBootstrap(AdministratorRepository administratorRepository, UserRepository userRepository,
//                        LessonRepository lessonRepository, RoleRepository roleRepository) {
//        this.administratorRepository = administratorRepository;
//        this.userRepository = userRepository;
//        this.lessonRepository = lessonRepository;
//        this.roleRepository = roleRepository;
//    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        loadRoles();
//        loadAdministrator();
//        loadStudents();
//        loadLesson();
//        assignUsersToDefaultRole();
    }

//    private void assignUsersToDefaultRole() {
//        List<Role> roles = (List<Role>) roleRepository.findAll();
//        List<User> users = (List<User>) userRepository.findAll();
//        List<Administrator> administrators = administratorRepository.findAll();
//
//        roles.forEach(role ->{
//            if(role.getName().equalsIgnoreCase("STUDENT")){
//                users.forEach(user -> {
//                    user.setRole(role);
//                    userRepository.save(user);
//                });
//            }
//        });
//
//        roles.forEach(role ->{
//            if(role.getName().equalsIgnoreCase("ADMIN")){
//                administrators.forEach(administrator -> {
//                    administrator.setRole(role);
//                    administratorRepository.save(administrator);
//                });
//            }
//        });
//    }
//
//    private void loadRoles(){
//        Role admin = new Role();
//        admin.setName("ADMIN");
//
//        Role studen = new Role();
//        studen.setName("STUDENT");
//
//        roleRepository.save(admin);
//        roleRepository.save(studen);
//    }
//
//    private void loadStudents(){
//
//        User user = new User();
//        user.setEmail("user@gmail.com");
//        user.setName("Name");
//        user.setSurName("Surname");
//        user.setFieldOfStudy("Informatyka");
//        user.setYearOfStudies(2);
//        user.setIndex("12345678901");
//        user.setPassword("stud");
//
//        userRepository.save(user);
//    }
//
//    private void loadLesson(){
//        Lesson lesson = new Lesson();
//        lesson.setTitle("JAva, Git, Spring");
//        lesson.setDescription("First lesson");
//        lesson.setTeacherInfo("Our teacher");
//        lessonRepository.save(lesson);
//    }
}
