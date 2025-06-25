package com.disenaclick.disenaclick.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.disenaclick.disenaclick.model.Pagina;
import com.disenaclick.disenaclick.model.Usuario;

@Repository
public interface PaginaRepository extends JpaRepository<Pagina, Long> {

    @Query("""
            SELECT p, p.usuario.nombres, p.plantilla.nombrePlantilla FROM Pagina p
            """)
    List<Object[]> findUsuarioPaginas();

    @Query("""
            SELECT p.nombrePagina, p.usuario.nombres, p.plantilla.nombrePlantilla
            FROM Pagina p
            """)
    List<Object[]> findUsuarioPlantillaConNombrePagina();

    @Query("""
            SELECT p.nombrePagina, p.usuario.nombres, p.plantilla.color
            FROM Pagina p
            """)
    List<Object[]> findNombrePaginaYUsuarioYColorPlantilla();

    @Query("""
                SELECT p.nombrePagina, p.fechaCreacion, p.usuario.nombres
                FROM Pagina p
                WHERE p.fechaCreacion > :fecha
            """)
    List<Object[]> findPaginasCreadasEnFecha(java.time.LocalDate fecha);

    List<Pagina> findByUsuarioId(Long usuarioId);

    List<Pagina> findByPlantillaId(Long plantillaId);

    List<Pagina> findByNombrePagina(String nombrePagina);

    List<Pagina> findByUsuario(Usuario usuario);

}
