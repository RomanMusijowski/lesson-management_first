package com.roman_musijowski.pgs_lessons.repositories;

import com.roman_musijowski.pgs_lessons.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryImp extends JpaRepository<User, Long> {
    User findByUserName(String userName);

}
