package com.disenaclick.disenaclick.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disenaclick.disenaclick.model.SeccionPagina;
import com.disenaclick.disenaclick.repository.SeccionPaginaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SeccionPaginaService {

    @Autowired
    private SeccionPaginaRepository seccionPaginaRepository;

    public List<SeccionPagina> findAll() {
        return seccionPaginaRepository.findAll();
    }

    public SeccionPagina findById(long id) {
        return seccionPaginaRepository.findById(id).orElse(null);
    }

    public SeccionPagina save(SeccionPagina seccionPagina) {
        return seccionPaginaRepository.save(seccionPagina);
    }

    public void delete(Long id) {
        SeccionPagina seccionPagina = seccionPaginaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sección de página no encontrada"));
        seccionPaginaRepository.delete(seccionPagina);
    }

    public SeccionPagina updateSeccionPagina(Long id, SeccionPagina seccionPagina) {
        SeccionPagina seccionPaginaToUpdate = seccionPaginaRepository.findById(id).orElse(null);
        if (seccionPaginaToUpdate != null) {
            seccionPaginaToUpdate.setTitulo(seccionPagina.getTitulo());
            seccionPaginaToUpdate.setParrafo(seccionPagina.getParrafo());
            return seccionPaginaRepository.save(seccionPaginaToUpdate);
        } else {
            return null;

        }
    }

    public SeccionPagina patchSeccionPagina(Long id, SeccionPagina parcialSeccionPagina) {
        SeccionPagina seccionPaginaToUpdate = seccionPaginaRepository.findById(id).orElse(null);
        if (seccionPaginaToUpdate != null) {
            if (parcialSeccionPagina.getTitulo() != null) {
                seccionPaginaToUpdate.setTitulo(parcialSeccionPagina.getTitulo());

            }
            if (parcialSeccionPagina.getParrafo() != null) {
                seccionPaginaToUpdate.setParrafo(parcialSeccionPagina.getParrafo());
            }
            return seccionPaginaRepository.save(seccionPaginaToUpdate);
        } else {
            return null;

        }
    }

    public List<SeccionPagina> findByTitulo(String titulo) {
        return seccionPaginaRepository.findBytitulo(titulo);
    }

}