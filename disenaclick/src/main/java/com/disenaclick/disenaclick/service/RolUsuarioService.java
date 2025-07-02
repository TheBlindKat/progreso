package com.disenaclick.disenaclick.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disenaclick.disenaclick.model.RolUsuario;
import com.disenaclick.disenaclick.model.Usuario;
import com.disenaclick.disenaclick.repository.RolUsuarioRepository;
import com.disenaclick.disenaclick.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RolUsuarioService {

    @Autowired
    private RolUsuarioRepository rolUsuarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    public List<RolUsuario> findAll() {
        return rolUsuarioRepository.findAll();
    }

    public RolUsuario findById(long id) {
        return rolUsuarioRepository.findById(id).orElse(null);
    }

    public RolUsuario save(RolUsuario rolUsuario) {
        return rolUsuarioRepository.save(rolUsuario);
    }

    public void delete(Long id) {
        RolUsuario rolUsuario = rolUsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RolUsuario no encontrado"));

        List<Usuario> usuarios = usuarioRepository.findByRolUsuario(rolUsuario);
        for (Usuario usuario : usuarios) {
            usuarioService.delete(Long.valueOf(usuario.getId()));
        }

        rolUsuarioRepository.delete(rolUsuario);
    }

    public RolUsuario updateRolUsuario(Long id, RolUsuario rolUsuario) {
        RolUsuario rolUsuarioToUpdate = rolUsuarioRepository.findById(id).orElse(null);
        if (rolUsuarioToUpdate != null) {
            rolUsuarioToUpdate.setNombreRolUsuario(rolUsuario.getNombreRolUsuario());
            return rolUsuarioRepository.save(rolUsuarioToUpdate);
        } else {
            return null;
        }
    }

    public RolUsuario patchRolUsuario(long id, RolUsuario parcialRolUsuario) {
        Optional<RolUsuario> rolUsuarioOptional = rolUsuarioRepository.findById(id);
        if (rolUsuarioOptional.isPresent()) {

            RolUsuario rolUsuarioToUpdate = rolUsuarioOptional.get();

            if (parcialRolUsuario.getNombreRolUsuario() != null) {
                rolUsuarioToUpdate.setNombreRolUsuario(parcialRolUsuario.getNombreRolUsuario());
            }
            return rolUsuarioRepository.save(rolUsuarioToUpdate);
        } else {
            return null;
        }
    }

}
