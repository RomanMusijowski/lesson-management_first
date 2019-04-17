package com.roman_musijowski.pgs_lessons.bootstrap;

import com.roman_musijowski.pgs_lessons.models.Lesson;
import com.roman_musijowski.pgs_lessons.models.User;
import com.roman_musijowski.pgs_lessons.models.security.Role;
import com.roman_musijowski.pgs_lessons.services.LessonService;
import com.roman_musijowski.pgs_lessons.services.RoleSevice;
import com.roman_musijowski.pgs_lessons.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private RoleSevice roleSevice;
    private LessonService lessonService;
    private UserService userService;

    @Autowired
    public DevBootstrap(RoleSevice roleSevice, LessonService lessonService, UserService userService) {
        this.roleSevice = roleSevice;
        this.lessonService = lessonService;
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadUsers();
        loadRoles();
        loadLesson();
        assignUsersToStudentRole();
        assignUsersToAdminRole();
       findUsers();
    }

    private void findUsers(){
        System.out.println(userService.findByUserName("admin@gmail.com").toString());
    }

    private void assignUsersToStudentRole() {
        List<Role> roles = (List<Role>) roleSevice.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role ->{
            if(role.getRole().equalsIgnoreCase("STUDENT")){
                users.forEach(user -> {
                    user.addRole(role);
                    userService.saveOrUpdate(user);
                });
            }
        });
    }

    private void assignUsersToAdminRole() {
        List<Role> roles = (List<Role>) roleSevice.listAll();
        List<User> users = (List<User>) userService.listAll();

//        for (User user : users) {
//            System.out.println(user.getUserName());
//        }

        roles.forEach(role ->{
            if(role.getRole().equalsIgnoreCase("ADMIN")){
                users.forEach(user -> {
                    if (user.getUserName().equals("admin@gmail.com")) {
                        user.addRole(role);
                        userService.saveOrUpdate(user);
                    }
                });
            }
        });
    }

    private void loadRoles(){
        Role admin = new Role();
        admin.setRole("ADMIN");
        roleSevice.saveOrUpdate(admin);

        Role studen = new Role();
        studen.setRole("STUDENT");
        roleSevice.saveOrUpdate(studen);
    }
//
    private void loadUsers(){

        User admin = new User();
        admin.setUserName("admin@gmail.com");
        admin.setName("Roman");
        admin.setSurName("Musiiovskyi");
        admin.setFieldOfStudy("Informatyka");
        admin.setYearOfStudies(2);
        admin.setIndex("89562312457");
        admin.setPassword("admin");

        userService.saveOrUpdate(admin);

        User user = new User();
        user.setUserName("pgnus@gmail.com");
        user.setName("Piotr");
        user.setSurName("Gnus");
        user.setFieldOfStudy("Informatyka");
        user.setYearOfStudies(2);
        user.setIndex("12345678901");
        user.setPassword("petro");

        userService.saveOrUpdate(user);

        User user2 = new User();
        user2.setUserName("rushla@gmail.com");
        user2.setName("Roman");
        user2.setSurName("Kushla");
        user2.setFieldOfStudy("Informatyka");
        user2.setYearOfStudies(2);
        user2.setIndex("45454545454");
        user2.setPassword("roman");

        userService.saveOrUpdate(user2);

        User user3 = new User();
        user3.setUserName("plabuda@gmail.com");
        user3.setName("Pavlo");
        user3.setSurName("Labuda");
        user3.setFieldOfStudy("Informatyka");
        user3.setYearOfStudies(2);
        user3.setIndex("12235645894");
        user3.setPassword("pavlo");

        userService.saveOrUpdate(user3);
    }

    private void loadLesson(){
        Lesson lesson = new Lesson();
        lesson.setTitle("JAva");
        lesson.setDescription("First lesson");
        lesson.setTeacherInfo("Dr. Koziol");
        lessonService.saveOrUpdate(lesson);

        Lesson lesson2 = new Lesson();
        lesson2.setTitle("Git");
        lesson2.setDescription("Second lesson");
        lesson2.setTeacherInfo("Dr. URban");
        lessonService.saveOrUpdate(lesson2);

        Lesson lesson3 = new Lesson();
        lesson3.setTitle("Spring");
        lesson3.setDescription("Third lesson");
        lesson3.setTeacherInfo("Dr. Nowojewski");
        lessonService.saveOrUpdate(lesson3);
    }
}
