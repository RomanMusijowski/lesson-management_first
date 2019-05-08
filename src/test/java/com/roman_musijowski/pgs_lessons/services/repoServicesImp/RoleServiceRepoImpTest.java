package com.roman_musijowski.pgs_lessons.services.repoServicesImp;

import com.roman_musijowski.pgs_lessons.models.security.Role;
import com.roman_musijowski.pgs_lessons.repositories.RoleRepositoryImp;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


class RoleServiceRepoImpTest {

    RoleServiceRepoImp service;
    Role role;

    @Mock
    RoleRepositoryImp roleRepositoryImp;

    @Before
    void setUp() {
        MockitoAnnotations.initMocks(this);

        service = new RoleServiceRepoImp(roleRepositoryImp);
        role = new Role();
        role.setRole_id(1);
        role.setRole("ADMIN");
    }

    @Test
    void listAll() {
        List<Role> roles = new ArrayList<>();

        Role role2 = new Role();
        role2 = new Role();
        role2.setRole_id(2);
        role2.setRole("STUDENT");

        roles.add(role);
        roles.add(role2);

        when(roleRepositoryImp.findAll()).thenReturn(roles);

        List<Role> roleList = service.listAll();

        assertNotNull(roles);
        assertEquals(2, roles.size());

        verify(roleRepositoryImp, times(1)).findAll();
    }

    @Test
    void getById() {
        when(roleRepositoryImp.getOne(any())).thenReturn(role);
        Role role  = service.getById(1L);

        assertNotNull(role);
    }

    @Test
    void saveOrUpdate() {
        Role saveOrUpdate = new Role();
        saveOrUpdate.setRole_id(3);
        saveOrUpdate.setRole("Customer");

        when(roleRepositoryImp.save(any())).thenReturn(role);

        Role savedRole = service.saveOrUpdate(saveOrUpdate);

        assertNotNull(savedRole);
        verify(roleRepositoryImp).save(any());
    }

    @Test
    void deleteById() {
        Role deleteRole = new Role();
        Integer id = 3;
        deleteRole.setRole_id(id);

        when(roleRepositoryImp.getOne(any())).thenReturn(deleteRole);
        Role actualRole = service.deleteById(Long.valueOf(id));

        verify(roleRepositoryImp, times(1)).getOne(any());
        verify(roleRepositoryImp, times(1)).deleteById(anyInt());
    }
}