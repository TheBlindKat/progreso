package com.disenaclick.disenaclick.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.disenaclick.disenaclick.model.RolUsuario;

@Repository
public interface RolUsuarioRepository extends JpaRepository<RolUsuario, Long> {

    List<RolUsuario> findBynombreRolUsuario(String nombreRolUsuario);

    // RolUsuario findById(Integer id);

}
