package com.roman_musijowski.pgs_lessons.services.repoServicesImp;

import com.roman_musijowski.pgs_lessons.models.security.Role;
import com.roman_musijowski.pgs_lessons.repositories.RoleRepository;
import com.roman_musijowski.pgs_lessons.services.RoleSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceRepoImp implements RoleSevice {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceRepoImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> listAll() {
        List<Role> roles= new ArrayList<>();
        roleRepository.findAll().forEach(roles::add);
        return roles;
    }

    @Override
    public Role getById(Long id) {
        return null;
    }

    @Override
    public Role getById(Integer id) {
        return roleRepository.getOne(id);
    }

    @Override
    public Role saveOrUpdate(Role object) {

        return roleRepository.save(object);
    }

    @Override
    public void delete(Role object) {
        roleRepository.delete(object);
    }

    @Override
    public void delete(Long id) {

    }
}
