package com.roman_musijowski.pgs_lessons.commands;

import javax.validation.constraints.*;

public class UserForm {

    private Long id;
    @NotEmpty
    private String userName;

    @NotEmpty
    @Size(min = 2, max = 36)
    private String name;

    @NotEmpty
    @Size(min = 2, max = 36)
    private String surName;

    //??????????????Size for Integer
    @NotNull
    @Min(0)
    private Integer yearOfStudies;

    @NotEmpty
    @Size(min = 5, max = 36)
    private String fieldOfStudy;

    @NotEmpty
    @Size(min = 11, max = 11)
    private String index;

    private String password;
    private String passwordTextConf;

//    private List<Lesson> lessons = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Integer getYearOfStudies() {
        return yearOfStudies;
    }

    public void setYearOfStudies(Integer yearOfStudies) {
        this.yearOfStudies = yearOfStudies;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordTextConf() {
        return passwordTextConf;
    }

    public void setPasswordTextConf(String passwordTextConf) {
        this.passwordTextConf = passwordTextConf;
    }

    //    public List<Lesson> getLessons() {
//        return lessons;
//    }
//
//    public void setLessons(List<Lesson> lessons) {
//        this.lessons = lessons;
//    }
}