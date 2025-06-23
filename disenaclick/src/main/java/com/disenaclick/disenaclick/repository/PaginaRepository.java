package com.disenaclick.disenaclick.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.disenaclick.disenaclick.model.Pagina;
import com.disenaclick.disenaclick.model.Plantilla;
import com.disenaclick.disenaclick.model.Usuario;

@Repository
public interface PaginaRepository extends JpaRepository<Pagina, Long> {

    @Query("""
            SELECT p, p.usuario.nombres, p.plantilla.nombrePlantilla FROM Pagina p
            """)
    List<Object[]> findUsuarioPaginas();

    List<Pagina> findByUsuarioId(Long usuarioId);

    List<Pagina> findByPlantillaId(Long plantillaId);

    List<Pagina> findByNombrePagina(String nombrePagina);

    List<Pagina> findByUsuario(Usuario usuario);

}
