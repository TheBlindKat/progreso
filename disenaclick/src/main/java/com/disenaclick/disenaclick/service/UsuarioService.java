package com.disenaclick.disenaclick.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disenaclick.disenaclick.model.Pagina;
import com.disenaclick.disenaclick.model.Usuario;
import com.disenaclick.disenaclick.repository.UsuarioRepository;
import com.disenaclick.disenaclick.repository.PaginaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PaginaRepository paginaRepository;

    @Autowired
    private PaginaService paginaService;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public List<Object[]> obtenerUsuariosConRolYPlantilla() {
        return usuarioRepository.findUsuarioRolPlantilla();
    };

    public List<Object[]> obtenerUsuariosConMasInfo() {
        return usuarioRepository.findUsuarioMasInfo();
    };

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void delete(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Pagina> paginas = paginaRepository.findByUsuario(usuario);

        for (Pagina pagina : paginas) {
            paginaService.delete(Long.valueOf(pagina.getId()));
        }

        usuarioRepository.delete(usuario);
        // usuarioRepository.deleteById(id);
    }

    public Usuario updateUsuario(Long id, Usuario usuario) {
        Usuario usuarioToUpdate = usuarioRepository.findById(id).orElse(null);
        if (usuarioToUpdate != null) {
            usuarioToUpdate.setNombres(usuario.getNombres());
            usuarioToUpdate.setApellidos(usuario.getApellidos());
            usuarioToUpdate.setPaginaUsuario(usuario.getPaginaUsuario());
            usuarioToUpdate.setRol(usuario.getRol());
            usuarioToUpdate.setRolUsuario(usuario.getRolUsuario());
            return usuarioRepository.save(usuarioToUpdate);
        } else {
            return null;
        }
    }

    public Usuario patchUsuario(Long id, Usuario parcialUsuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {

            Usuario usuarioToUpdate = usuarioOptional.get();

            if (parcialUsuario.getNombres() != null) {
                usuarioToUpdate.setNombres(parcialUsuario.getNombres());
            }

            if (parcialUsuario.getApellidos() != null) {
                usuarioToUpdate.setApellidos(parcialUsuario.getApellidos());
            }

            if (parcialUsuario.getPaginaUsuario() != null) {
                usuarioToUpdate.setPaginaUsuario(parcialUsuario.getPaginaUsuario());
            }
            if (parcialUsuario.getRol() != null) {
                usuarioToUpdate.setRol(parcialUsuario.getRol());
            }
            if (parcialUsuario.getRolUsuario() != null) {
                usuarioToUpdate.setRolUsuario(parcialUsuario.getRolUsuario());
            }
            return usuarioRepository.save(usuarioToUpdate);

        } else {
            return null;
        }

    }

    public List<Usuario> findByNombresAndApellidos(String nombres, String apellidos) {
        return usuarioRepository.findByNombresAndApellidos(nombres, apellidos);

    }

}
