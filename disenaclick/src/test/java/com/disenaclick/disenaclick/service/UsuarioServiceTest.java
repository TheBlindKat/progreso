package com.disenaclick.disenaclick.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.disenaclick.disenaclick.model.Plantilla;
import com.disenaclick.disenaclick.model.Rol;
import com.disenaclick.disenaclick.model.RolUsuario;
import com.disenaclick.disenaclick.model.Usuario;
import com.disenaclick.disenaclick.repository.UsuarioRepository;

@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @MockBean
    private UsuarioRepository usuarioRepository;

    private Usuario createUsuario() {
        return new Usuario(1,
                "ricardo@duocuc.cl",
                "testcontrasena",
                "Ricardo Hernan",
                "Droguet Montecinos",
                "www.paginatest.cl",
                new Rol(),
                new RolUsuario(),
                new Plantilla(), null); // Aquí se pueden ajustar los parámetros del model Usuario
    }

    @Test
    public void testFindAll() {
        when(usuarioRepository.findAll()).thenReturn(List.of(createUsuario()));
        List<Usuario> usuarios = usuarioService.findAll();
        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
    }

    @Test
    public void testFindById() {
        when(usuarioRepository.findById(1L)).thenReturn(java.util.Optional.of(createUsuario()));
        Usuario usuario = usuarioService.findById(1L);
        assertNotNull(usuario);
        assertEquals("ricardo@duocuc,cl", usuario.getCorreo());
        assertEquals("Ricardo Hernan", usuario.getNombres());
        assertEquals("Droguet Montecinos", usuario.getApellidos());
    }

    @Test
    public void testSave() {
        Usuario usuario = createUsuario();
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        Usuario savedUsuario = usuarioService.save(usuario);
        assertNotNull(savedUsuario);
        assertEquals("ricardo@duocuc,cl", savedUsuario.getCorreo());
        assertEquals("Ricardo Hernan", savedUsuario.getNombres());
        assertEquals("Droguet Montecinos", savedUsuario.getApellidos());
    }

    @Test
    void testPatchUsuario() {
        Usuario existingUsuario = createUsuario();
        Usuario patchData = new Usuario();
        patchData.setCorreo("ricardo123@duocuc.cl");
        patchData.setNombres("Ricardo Leonardo");
        patchData.setApellidos("Droguet Montecinos");

        when(usuarioRepository.findById(1L)).thenReturn(java.util.Optional.of(existingUsuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(existingUsuario);

        Usuario patchedUsuario = usuarioService.patchUsuario(1L, patchData);
        assertNotNull(patchedUsuario);
        assertEquals("ricardo123@duocuc.cl", patchedUsuario.getCorreo());
        assertEquals("Ricardo Leonardo", patchedUsuario.getNombres());
        assertEquals("Droguet Montecinos", patchedUsuario.getApellidos());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(usuarioRepository).deleteById(1L);
        usuarioService.delete(1l);
        verify(usuarioRepository, times(1)).deleteById(1L);
    }

}
