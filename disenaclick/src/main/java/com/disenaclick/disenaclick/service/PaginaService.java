package com.disenaclick.disenaclick.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disenaclick.disenaclick.model.Pagina;
import com.disenaclick.disenaclick.repository.PaginaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PaginaService {

    @Autowired
    private PaginaRepository paginaRepository;

    public List<Pagina> findAll() {
        return paginaRepository.findAll();
    }

    public List<Pagina> findByNombrePagina(String nombrePagina) {
        return paginaRepository.findByNombrePagina(nombrePagina);
    }

    public List<Object[]> obtenerUsuarioPaginas() {
        return paginaRepository.findUsuarioPaginas();
    }

    public Pagina findById(Long id) {
        return paginaRepository.findById(id).orElse(null);
    }

    public List<Pagina> findByPlantillaId(Long plantillaId) {
        return paginaRepository.findByPlantillaId(plantillaId);
    }

    public Pagina save(Pagina pagina) {
        return paginaRepository.save(pagina);
    }

    public void delete(Long id) {
        paginaRepository.deleteById(id);
    }

    public Pagina updatePagina(Long id, Pagina pagina) {
        Pagina paginaToUpdate = paginaRepository.findById(id).orElse(null);
        if (paginaToUpdate != null) {
            paginaToUpdate.setNombrePagina(pagina.getNombrePagina());
            paginaToUpdate.setFechaCreacion(pagina.getFechaCreacion());
            paginaToUpdate.setUsuario(pagina.getUsuario());
            return paginaRepository.save(paginaToUpdate);
        } else {
            return null;
        }
    }

    public Pagina patchPagina(Long id, Pagina parcialPagina) {
        Pagina paginaToUpdate = paginaRepository.findById(id).orElse(null);
        if (paginaToUpdate != null) {

            if (parcialPagina.getNombrePagina() != null) {
                paginaToUpdate.setNombrePagina(parcialPagina.getNombrePagina());
            }
            if (parcialPagina.getFechaCreacion() != null) {
                paginaToUpdate.setFechaCreacion(parcialPagina.getFechaCreacion());
            }
            if (parcialPagina.getUsuario() != null) {
                paginaToUpdate.setUsuario(parcialPagina.getUsuario());
            }
            return paginaRepository.save(paginaToUpdate);
        } else {
            return null;
        }
    }

}