package com.roman_musijowski.pgs_lessons.services;

import java.util.List;


public interface CRUDService<T> {

    List<?> listAll();

    T getById(Long id);

    T saveOrUpdate(T object);

    T deleteById(Long id);
}
