package com.roman_musijowski.pgs_lessons.services.repoServicesImp;

import com.roman_musijowski.pgs_lessons.commands.LessonForm;
import com.roman_musijowski.pgs_lessons.converters.LessonFormToLesson;
import com.roman_musijowski.pgs_lessons.models.Lesson;
import com.roman_musijowski.pgs_lessons.models.User;
import com.roman_musijowski.pgs_lessons.repositories.LessonRepository;
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

    private LessonRepository lessonRepository;
    private LessonFormToLesson lessonFormToLesson;
    private UserService userService;

    @Autowired
    public LessonServiceRepoImp(UserService userService, LessonRepository lessonRepository, LessonFormToLesson lessonFormToLesson) {
        this.lessonRepository = lessonRepository;
        this.lessonFormToLesson = lessonFormToLesson;
        this.userService = userService;
    }

    @Override
    public List<Lesson> listAll() {

        List<Lesson> lessons = new ArrayList<>();
        lessonRepository.findAll().forEach(lessons::add);
        return lessons;
    }

    @Override
    public Lesson getById(Long id) {
        return lessonRepository.getOne(id);
    }

    @Override
    public Lesson getById(Integer id) {
        return null;
    }

    @Override
    public Lesson saveOrUpdate(Lesson object) {

        return lessonRepository.save(object);
    }

    @Override
    public void delete(Lesson object) {
        lessonRepository.delete(object);
    }

    @Override
    public void delete(Long id) {
        System.out.println("Delete in service");

        Lesson lesson = getById(id);
        Set<User> users = lesson.getUsers();

        //deleting lessons from users
        users.forEach(user -> {
            User user1 = userService.getById(user.getId());
            System.out.println(user1.toString());

            if (user1.getLessons().contains(lesson)){
                user1.getLessons().remove(lesson);
                System.out.println("Lesson removed");
            }
            userService.saveOrUpdate(user1);
            System.out.println("User removed");
        });

        //deleting users from lesson
        for (Iterator<User> iterator = lesson.getUsers().iterator(); iterator.hasNext();) {
            User user = iterator.next();
            System.out.println(iterator.toString());
            iterator.remove();
            System.out.println("Remove user from lesson!");
        }

        lessonRepository.deleteById(id);
        System.out.println("Lesson deleted!");
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
