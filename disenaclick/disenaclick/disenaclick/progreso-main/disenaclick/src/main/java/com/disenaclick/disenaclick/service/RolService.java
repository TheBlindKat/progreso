package com.disenaclick.disenaclick.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disenaclick.disenaclick.model.Rol;
import com.disenaclick.disenaclick.repository.RolRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RolService {

    @Autowired
    private RolRepository rolRepository;

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
        rolRepository.deleteById(id);
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
