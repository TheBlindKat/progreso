package com.disenaclick.disenaclick.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;
import com.disenaclick.disenaclick.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("""
            SELECT u, u.rol.nombreRol, u.plantilla.nombrePlantilla FROM Usuario u
            """)
    List<Object[]> findUsuarioRolPlantilla();

    List<Usuario> findByNombres(String nombres);

    Usuario findByCorreo(String correo);

    List<Usuario> findByNombresAndApellidos(String nombres, String apellidos);

    Usuario findById(Integer id);

}
