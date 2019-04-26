package com.roman_musijowski.pgs_lessons.services.repoServicesImp;

import com.roman_musijowski.pgs_lessons.models.security.Role;
import com.roman_musijowski.pgs_lessons.repositories.RoleRepositoryImp;
import com.roman_musijowski.pgs_lessons.services.RoleSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceRepoImp implements RoleSevice {

    private RoleRepositoryImp roleRepositoryImp;

    @Autowired
    public RoleServiceRepoImp(RoleRepositoryImp roleRepositoryImp) {
        this.roleRepositoryImp = roleRepositoryImp;
    }

    @Override
    public List<Role> listAll() {
        List<Role> roles= new ArrayList<>();
        roleRepositoryImp.findAll().forEach(roles::add);
        return roles;
    }

    @Override
    public Role getById(Long id) {
        return roleRepositoryImp.getOne(Math.toIntExact(id));
    }

    @Override
    public Role saveOrUpdate(Role object) {

        return roleRepositoryImp.save(object);
    }

    @Override
    public void delete(Long id) {
        roleRepositoryImp.deleteById(Math.toIntExact(id));

    }
}
