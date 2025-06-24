package com.disenaclick.disenaclick.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.disenaclick.disenaclick.model.RolUsuario;
import com.disenaclick.disenaclick.repository.RolUsuarioRepository;

@SpringBootTest
public class RolUsuarioServiceTest {

    @Autowired
    private RolUsuarioService rolUsuarioService;

    @MockBean
    private RolUsuarioRepository rolUsuarioRepository;

    private RolUsuario createRolUsuario() {
        return new RolUsuario(1, "Administrador de plataforma");
    }

    @Test
    public void testFindAll() {
        when(rolUsuarioRepository.findAll()).thenReturn(List.of(createRolUsuario()));
        List<RolUsuario> rolesus = rolUsuarioService.findAll();
        assertNotNull(rolesus);
        assertEquals(1, rolesus.size());
        assertEquals("Administrador de plataforma", rolesus.get(0).getNombreRolUsuario());
    }

    @Test
    public void testFindById() {
        when(rolUsuarioRepository.findById(1L)).thenReturn(java.util.Optional.of(createRolUsuario()));
        RolUsuario rolUsuario = rolUsuarioService.findById(1L);
        assertNotNull(rolUsuario);
        assertEquals("Administrador de plataforma", rolUsuario.getNombreRolUsuario());
    }

    @Test
    public void testSave() {
        RolUsuario rolUsuario = createRolUsuario();
        when(rolUsuarioRepository.save(rolUsuario)).thenReturn(rolUsuario);
        RolUsuario savedRolUsuario = rolUsuarioService.save(rolUsuario);
        assertNotNull(savedRolUsuario);
        assertEquals("Administrador de plataforma", savedRolUsuario.getNombreRolUsuario());
    }

    @Test
    public void testPatchRolUsuario() {
        RolUsuario exitingRolUsuario = createRolUsuario();
        RolUsuario patchData = new RolUsuario();
        patchData.setNombreRolUsuario("Operador de plataforma");
        when(rolUsuarioRepository.findById(1L)).thenReturn(java.util.Optional.of(exitingRolUsuario));
        when(rolUsuarioRepository.save(any(RolUsuario.class))).thenReturn(patchData);

        RolUsuario patchedRolUsuario = rolUsuarioService.patchRolUsuario(1, patchData);
        assertNotNull(patchedRolUsuario);
        assertEquals("Operador de plataforma", patchedRolUsuario.getNombreRolUsuario());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(rolUsuarioRepository).deleteById(1L);
        rolUsuarioService.delete(1L);
        verify(rolUsuarioRepository, times(1)).deleteById(1L);
    }
}
