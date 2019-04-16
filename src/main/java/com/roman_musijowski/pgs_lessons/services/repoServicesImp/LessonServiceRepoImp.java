package com.roman_musijowski.pgs_lessons.services.repoServicesImp;

import com.roman_musijowski.pgs_lessons.commands.LessonForm;
import com.roman_musijowski.pgs_lessons.converters.LessonFormToLesson;
import com.roman_musijowski.pgs_lessons.models.Lesson;
import com.roman_musijowski.pgs_lessons.repositories.LessonRepository;
import com.roman_musijowski.pgs_lessons.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonServiceRepoImp implements LessonService {

    private LessonRepository lessonRepository;
    private LessonFormToLesson lessonFormToLesson;

    @Autowired
    public LessonServiceRepoImp(LessonRepository lessonRepository, LessonFormToLesson lessonFormToLesson) {
        this.lessonRepository = lessonRepository;
        this.lessonFormToLesson = lessonFormToLesson;
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
        lessonRepository.deleteById(id);
    }

    @Override
    public Lesson saveOrUpdateLessonForm(LessonForm lessonForm) {
        Lesson newLesson = lessonFormToLesson.convert(lessonForm);

        if (newLesson.getLesson_id() != null){
            Lesson existingLesson = getById(newLesson.getLesson_id());

        }
        return saveOrUpdate(newLesson);
    }
}
