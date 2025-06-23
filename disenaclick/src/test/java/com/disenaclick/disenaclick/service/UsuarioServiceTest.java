package com.disenaclick.disenaclick.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.disenaclick.disenaclick.model.Pagina;
import com.disenaclick.disenaclick.model.Plantilla;
import com.disenaclick.disenaclick.model.Rol;
import com.disenaclick.disenaclick.model.RolUsuario;
import com.disenaclick.disenaclick.model.Usuario;
import com.disenaclick.disenaclick.repository.UsuarioRepository;

@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario createUsuario() {
        return new Usuario(1, "Usuario@Test.cl", "contra123", "Juan Carlos", "Bodoque", "Las Noticias de Bodoque",
                new Rol(), new RolUsuario(),
                new Plantilla(),
                new ArrayList<Pagina>());
    }

}
