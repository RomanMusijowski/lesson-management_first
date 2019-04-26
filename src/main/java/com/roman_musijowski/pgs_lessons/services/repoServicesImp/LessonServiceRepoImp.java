package com.roman_musijowski.pgs_lessons.services.repoServicesImp;

import com.roman_musijowski.pgs_lessons.commands.LessonForm;
import com.roman_musijowski.pgs_lessons.converters.LessonFormToLesson;
import com.roman_musijowski.pgs_lessons.models.Lesson;
import com.roman_musijowski.pgs_lessons.models.User;
import com.roman_musijowski.pgs_lessons.repositories.LessonRepositoryImp;
import com.roman_musijowski.pgs_lessons.services.LessonService;
import com.roman_musijowski.pgs_lessons.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class LessonServiceRepoImp implements LessonService {

    private LessonRepositoryImp lessonRepositoryImp;
    private LessonFormToLesson lessonFormToLesson;
    private UserService userService;

    @Autowired
    public LessonServiceRepoImp(UserService userService, LessonRepositoryImp lessonRepositoryImp, LessonFormToLesson lessonFormToLesson) {
        this.lessonRepositoryImp = lessonRepositoryImp;
        this.lessonFormToLesson = lessonFormToLesson;
        this.userService = userService;
    }

    @Override
    public List<Lesson> listAll() {

        List<Lesson> lessons = new ArrayList<>();
        lessonRepositoryImp.findAll().forEach(lessons::add);
        return lessons;
    }

    @Override
    public Lesson getById(Long id) {
        return lessonRepositoryImp.getOne(id);
    }

    @Override
    public Lesson saveOrUpdate(Lesson object) {
        return lessonRepositoryImp.save(object);
    }

    @Override
    public void delete(Long id) {
        System.out.println("Delete in service");

        Lesson lesson = getById(id);
        if (lesson != null) {
            Set<User> users = lesson.getUsers();

            //deleting lessons from users
            users.forEach(user -> {
                User user1 = userService.getById(user.getId());
                System.out.println(user1.toString());

                if (user1.getLessons().contains(lesson)) {
                    user1.getLessons().remove(lesson);
                    System.out.println("Lesson removed");
                }
                userService.saveOrUpdate(user1);
                System.out.println("User removed");
            });

            //deleting users from lesson
            for (Iterator<User> iterator = lesson.getUsers().iterator(); iterator.hasNext(); ) {
                User user = iterator.next();
                System.out.println(iterator.toString());
                iterator.remove();
                System.out.println("Remove user from lesson!");
            }

            lessonRepositoryImp.deleteById(id);
            System.out.println("Lesson deleted!");

        }else {
            lessonRepositoryImp.deleteById(id);
            System.out.println("Lesson deleted!");

        }
    }

    @Override
    public Lesson saveOrUpdateLessonForm(LessonForm lessonForm) {
        Lesson newLesson = lessonFormToLesson.convert(lessonForm);

        if (newLesson.getLesson_id() != null){
            Lesson existingLesson = getById(newLesson.getLesson_id());

            newLesson.setUsers(existingLesson.getUsers());
        }
        return saveOrUpdate(newLesson);
    }
}
