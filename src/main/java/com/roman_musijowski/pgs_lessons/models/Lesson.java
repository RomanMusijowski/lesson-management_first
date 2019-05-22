package com.roman_musijowski.pgs_lessons.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long lessonId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "teacherInfo")
    private String teacherInfo;

    @Column(name = "date")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime date;

    @ManyToMany(mappedBy = "lessons", fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<>();

    public Lesson() { }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getTeacherInfo() {
        return teacherInfo;
    }

    public void setTeacherInfo(String teacherInfo) {
        this.teacherInfo = teacherInfo;
    }

    public void addUser(User user){
        if(!this.users.contains(user)){
            this.users.add(user);
        }

        if(!user.getLessons().contains(this)){
            user.getLessons().add(this);
        }
    }

    public void removeUser(User user){
        this.users.remove(user);
        user.getLessons().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return Objects.equals(lessonId, lesson.lessonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lessonId);
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "lessonId=" + lessonId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", teacherInfo='" + teacherInfo + '\'' +
                ", date=" + date +
                ", users=" + users +
                '}';
    }
}