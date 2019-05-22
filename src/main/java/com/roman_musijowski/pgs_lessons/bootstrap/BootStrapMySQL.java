package com.roman_musijowski.pgs_lessons.bootstrap;

import com.roman_musijowski.pgs_lessons.models.Lesson;
import com.roman_musijowski.pgs_lessons.models.User;
import com.roman_musijowski.pgs_lessons.models.security.Role;
import com.roman_musijowski.pgs_lessons.services.LessonService;
import com.roman_musijowski.pgs_lessons.services.RoleSevice;
import com.roman_musijowski.pgs_lessons.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Profile({"dev", "prod"})
public class BootStrapMySQL implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(DevBootstrap.class);


    private RoleSevice roleSevice;
    private LessonService lessonService;
    private UserService userService;

    @Autowired
    public BootStrapMySQL(RoleSevice roleSevice, LessonService lessonService, UserService userService) {
        this.roleSevice = roleSevice;
        this.lessonService = lessonService;
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadUsers();
        loadRoles();
        loadLesson();
        assignUsersToStudentRole();
        assignUsersToAdminRole();
        assignUserToLesson();
    }

    private void assignUserToLesson() {
        logger.info("Assign user to lessons");
        List<User> users = (List<User>) userService.listAll();
        List<Lesson> lessons = (List<Lesson>) lessonService.listAll();

        users.forEach(user -> {
            System.out.println(user.toString());

            lessons.forEach(lesson -> {
                if (lesson.getTitle() == "Java" && user.getUserName() != "admin@gmail.com") {
                    user.addLesson(lesson);

                }else if (lesson.getTitle() == "Git" && user.getUserName() != "admin@gmail.com"
                        && user.getUserName() != "plabuda@gmail.com"){
                    user.addLesson(lesson);

                }else if (lesson.getTitle() == "Spring" && user.getUserName() != "admin@gmail.com"
                        && user.getUserName() != "plabuda@gmail.com"
                        && user.getUserName() != "rushla@gmail.com"){
                    user.addLesson(lesson);
                }
            });
            userService.saveOrUpdate(user);
        });
    }

    private void assignUsersToStudentRole() {
        logger.info("Assign user to STUDENT role");
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
        logger.info("Assign user to ADMIN role");
        List<Role> roles = (List<Role>) roleSevice.listAll();
        List<User> users = (List<User>) userService.listAll();

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
        logger.info("Load roles");

        Role admin = new Role();
        admin.setRole("ADMIN");
        roleSevice.saveOrUpdate(admin);

        Role studen = new Role();
        studen.setRole("STUDENT");
        roleSevice.saveOrUpdate(studen);
    }

    private void loadUsers(){
        logger.info("Load users");

        User admin = new User();
        admin.setUserName("admin@gmail.com");
        admin.setName("Admin");
        admin.setSurName("Admin");
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
        logger.info("Load lessons");
        LocalDateTime localDateTime = LocalDateTime.of(2019,05,15,18,30);

        Lesson lesson = new Lesson();
        lesson.setTitle("Java");
        lesson.setDescription("First lesson");
        lesson.setTeacherInfo("Dr. Koziol");
        lesson.setDate(localDateTime);
        lessonService.saveOrUpdate(lesson);

        localDateTime = LocalDateTime.of(2019,06,11,18,00);

        Lesson lesson2 = new Lesson();
        lesson2.setTitle("Git");
        lesson2.setDescription("Second lesson");
        lesson2.setTeacherInfo("Dr. URban");
        lesson2.setDate(localDateTime);
        lessonService.saveOrUpdate(lesson2);

        localDateTime = LocalDateTime.of(2019,07,8,19,00);


        Lesson lesson3 = new Lesson();
        lesson3.setTitle("Spring");
        lesson3.setDescription("Third lesson");
        lesson3.setTeacherInfo("Dr. Nowojewski");
        lesson3.setDate(localDateTime);
        lessonService.saveOrUpdate(lesson3);
    }
}
