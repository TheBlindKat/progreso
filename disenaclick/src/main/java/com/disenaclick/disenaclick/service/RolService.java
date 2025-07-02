package com.disenaclick.disenaclick.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disenaclick.disenaclick.model.Rol;
import com.disenaclick.disenaclick.model.Usuario;
import com.disenaclick.disenaclick.repository.RolRepository;
import com.disenaclick.disenaclick.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    public List<Rol> findAll() {
        return rolRepository.findAll();
    }

    public Rol findById(long id) {
        return rolRepository.findById(id).orElse(null);
    }

    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }

    public void delete(Long id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        List<Usuario> usuarios = usuarioRepository.findByRol(rol);
        for (Usuario usuario : usuarios) {
            usuarioService.delete(Long.valueOf(usuario.getId()));
        }
        rolRepository.delete(rol);
    }

    public Rol updateRol(Long id, Rol rol) {
        Rol rolToUpdate = rolRepository.findById(id).orElse(null);
        if (rolToUpdate != null) {
            rolToUpdate.setNombreRol(rol.getNombreRol());
            return rolRepository.save(rolToUpdate);
        } else {
            return null;
        }
    }

    public Rol patchRol(long id, Rol parcialRol) {
        Optional<Rol> rolOptional = rolRepository.findById(id);
        if (rolOptional.isPresent()) {

            Rol rolToUpdate = rolOptional.get();

            if (parcialRol.getNombreRol() != null) {
                rolToUpdate.setNombreRol(parcialRol.getNombreRol());
            }
            return rolRepository.save(rolToUpdate);
        } else {
            return null;
        }
    }
}
