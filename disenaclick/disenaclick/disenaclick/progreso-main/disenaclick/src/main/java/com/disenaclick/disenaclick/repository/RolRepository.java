package com.disenaclick.disenaclick.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.disenaclick.disenaclick.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    List<Rol> findByNombreRol(String nombreRol);

}