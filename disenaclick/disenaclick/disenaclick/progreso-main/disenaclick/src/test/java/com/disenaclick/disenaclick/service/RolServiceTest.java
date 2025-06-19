package com.disenaclick.disenaclick.service;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;


import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.disenaclick.disenaclick.model.Rol;
import com.disenaclick.disenaclick.repository.RolRepository;



@SpringBootTest

public class RolServiceTest {

    @Autowired
    private RolService rolService;



    @MockBean
    private RolRepository rolRepository;



    private Rol createRol() {
    return new Rol(1, "rol 1");
    }



    @Test

    public void testFindAll(){

        when(rolRepository.findAll()).thenReturn(List.of(createRol()));
        List<Rol> roles = rolService.findAll();
        assertNotNull(roles);
        assertEquals(1, roles.size());

    }



    @Test

    public void testFindById() {
        when(rolRepository.findById(1L)).thenReturn(Optional.of(createRol()));
        Rol rol = rolService.findById(1L);
        assertNotNull(rol);
        assertEquals("rol 1", rol.getNombreRol());
    }



    @Test
    public void testSave() {
        Rol rol = createRol();
        when(rolRepository.save(rol)).thenReturn(rol);
        Rol savedRol = rolService.save(rol);
        assertNotNull(savedRol);
        assertEquals("rol 1", savedRol.getNombreRol());
    }

}