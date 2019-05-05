package com.roman_musijowski.pgs_lessons.services.repoServicesImp;

import com.roman_musijowski.pgs_lessons.models.security.Role;
import com.roman_musijowski.pgs_lessons.repositories.RoleRepositoryImp;
import com.roman_musijowski.pgs_lessons.services.RoleSevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceRepoImp implements RoleSevice {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceRepoImp.class);


    private RoleRepositoryImp roleRepositoryImp;

    @Autowired
    public RoleServiceRepoImp(RoleRepositoryImp roleRepositoryImp) {
        this.roleRepositoryImp = roleRepositoryImp;
    }

    @Override
    @Cacheable(value = "roles")
    public List<Role> listAll() {
        logger.info("Get list of roles");

        List<Role> roles= new ArrayList<>();
        roleRepositoryImp.findAll().forEach(roles::add);
        return roles;
    }

    @Override
    @Cacheable(cacheNames = "roles", key = "#id")
    public Role getById(Long id) {
        logger.info("Get role - " + id);

        return roleRepositoryImp.getOne(Math.toIntExact(id));
    }

    @Override
    @CacheEvict(cacheNames = "roles", allEntries = true)
    public Role saveOrUpdate(Role object) {
        logger.info("Save role - " + object.toString());

        return roleRepositoryImp.save(object);
    }

    @Override
    @CacheEvict(cacheNames = "roles", key = "#id")
    public Role deleteById(Long id) {
        logger.info("Delete role - " + id);
        Role role = getById(id);

        roleRepositoryImp.deleteById(Math.toIntExact(id));
        return role;
    }
}
