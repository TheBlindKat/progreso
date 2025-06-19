package com.disenaclick.disenaclick.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.disenaclick.disenaclick.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findByNombreNegocio(String nombreNegocio);

    // List<Categoria> findById(Integer id);

}
