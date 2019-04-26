package com.roman_musijowski.pgs_lessons.repositories;

import com.roman_musijowski.pgs_lessons.models.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepositoryImp extends JpaRepository<Role, Integer> {
}
