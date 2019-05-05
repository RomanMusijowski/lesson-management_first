package com.roman_musijowski.pgs_lessons.services.repoServicesImp;

import com.roman_musijowski.pgs_lessons.commands.LessonForm;
import com.roman_musijowski.pgs_lessons.converters.LessonFormToLesson;
import com.roman_musijowski.pgs_lessons.models.Lesson;
import com.roman_musijowski.pgs_lessons.models.User;
import com.roman_musijowski.pgs_lessons.repositories.LessonRepositoryImp;
import com.roman_musijowski.pgs_lessons.services.LessonService;
import com.roman_musijowski.pgs_lessons.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class LessonServiceRepoImp implements LessonService {

    private static final Logger logger = LoggerFactory.getLogger(LessonServiceRepoImp.class);

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
    @Cacheable(value = "lessons")
    public List<Lesson> listAll() {
        logger.info("Get list of lessons");

        List<Lesson> lessons = new ArrayList<>();
        lessonRepositoryImp.findAll().forEach(lessons::add);
        return lessons;
    }

    @Override
    @Cacheable(cacheNames = "lessons", key = "#id")
    public Lesson getById(Long id) {
        logger.info("Get lesson - " + id);
        return lessonRepositoryImp.getOne(id);
    }

    @Override
    @Caching(evict =
            {@CacheEvict(cacheNames = "users", allEntries = true),
            @CacheEvict(cacheNames = "lessons", allEntries = true)})
    public Lesson saveOrUpdate(Lesson object) {
        logger.info("Save lesson - " + object.toString());
        return lessonRepositoryImp.save(object);
    }

    @Override
    @CacheEvict(cacheNames = "lessons", allEntries = true)
    public Lesson saveOrUpdateLessonForm(LessonForm lessonForm) {
        logger.info("Save lessonForm - " + lessonForm);
        Lesson newLesson = lessonFormToLesson.convert(lessonForm);

        if (newLesson.getLesson_id() != null){
            Lesson existingLesson = getById(newLesson.getLesson_id());

            newLesson.setUsers(existingLesson.getUsers());
        }
        return saveOrUpdate(newLesson);
    }

    @Override
    @CacheEvict(cacheNames = "lessons", key = "#id")
    public Lesson deleteById(Long id) {
        logger.info("Delete lesson - " + id);

        Lesson lesson = getById(id);

        try {
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

        }catch (NullPointerException e){
            logger.warn("Error get list of users for existing lesson");
            System.out.println("This lesson have not users" + e);
        }finally {

            System.out.println("Lesson deleted");
            return lesson;
        }
    }
}
