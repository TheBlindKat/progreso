package com.disenaclick.disenaclick.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.disenaclick.disenaclick.model.SeccionPagina;

@Repository
public interface SeccionPaginaRepository extends JpaRepository <SeccionPagina, Long>{

    List<SeccionPagina> findBytitulo(String titulo);
    
    SeccionPagina findById(Integer id);

}
