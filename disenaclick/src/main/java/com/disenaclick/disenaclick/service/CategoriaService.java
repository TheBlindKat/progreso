package com.disenaclick.disenaclick.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disenaclick.disenaclick.model.Categoria;
import com.disenaclick.disenaclick.repository.CategoriaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria findById(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void delete(Long id) {

        /*
         * Categoria categoria = categoriaRepository.findById(id)
         * .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
         */
        categoriaRepository.deleteById(id);

    }

    public Categoria updateCategoria(Long id, Categoria categoria) {
        Categoria categoriaToUpdate = categoriaRepository.findById(id).orElse(null);
        if (categoriaToUpdate != null) {
            categoriaToUpdate.setNombreNegocio(categoria.getNombreNegocio());
            categoriaToUpdate.setTipo(categoria.getTipo());
            categoriaToUpdate.setUrleNegocio(categoria.getUrleNegocio());
            return categoriaRepository.save(categoriaToUpdate);
        } else {
            return null;
        }
    }

    public Categoria patchCategoria(Long id, Categoria parcialCategoria) {
        Categoria categoriaToUpdate = categoriaRepository.findById(id).orElse(null);
        if (categoriaToUpdate != null) {
            if (parcialCategoria.getNombreNegocio() != null) {
                categoriaToUpdate.setNombreNegocio(parcialCategoria.getNombreNegocio());
            }
            if (parcialCategoria.getTipo() != null) {
                categoriaToUpdate.setTipo(parcialCategoria.getTipo());
            }
            if (parcialCategoria.getUrleNegocio() != null) {
                categoriaToUpdate.setUrleNegocio(parcialCategoria.getUrleNegocio());
            }
            return categoriaRepository.save(categoriaToUpdate);
        } else {
            return null;
        }
    }

    public List<Categoria> findByNombreNegocio(String NombreNegocio) {
        return categoriaRepository.findByNombreNegocio(NombreNegocio);
    }

}
