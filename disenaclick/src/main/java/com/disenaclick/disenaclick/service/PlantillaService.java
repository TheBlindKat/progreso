package com.disenaclick.disenaclick.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disenaclick.disenaclick.model.Pagina;
import com.disenaclick.disenaclick.model.Plantilla;
import com.disenaclick.disenaclick.repository.PaginaRepository;
import com.disenaclick.disenaclick.repository.PlantillaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PlantillaService {

    @Autowired
    private PlantillaRepository plantillaRepository;

    @Autowired
    private PaginaRepository paginaRepository;

    @Autowired
    private PaginaService paginaService;

    public List<Plantilla> findAll() {
        return plantillaRepository.findAll();
    }

    public Plantilla findById(Long id) {
        return plantillaRepository.findById(id).orElse(null);
    }

    public Plantilla save(Plantilla plantilla) {
        return plantillaRepository.save(plantilla);
    }

    public void delete(Long id) {
        Plantilla plantilla = plantillaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plantilla no encontrada"));

        List<Pagina> paginas = paginaRepository.findByPlantillaId(id);
        for (Pagina pagina : paginas) {
            paginaService.delete(Long.valueOf(pagina.getId()));
        }

        plantillaRepository.delete(plantilla);
    }

    /*
     * Plantilla plantilla = plantillaRepository.findById(id)
     * .orElseThrow(() -> new RuntimeException("Plantilla no encontrada"));
     * // plantillaRepository.deleteById(id);
     * List<Pagina> paginas = paginaRepository.findByPlantillaId(id);
     * 
     * for (Pagina pagina : paginas) {
     * paginaService.delete(Long.valueOf(pagina.getId()));
     * }
     * plantillaRepository.delete(plantilla);
     * }
     */

    public Plantilla updatePlantilla(Long id, Plantilla plantilla) {
        Plantilla plantillaToUpdate = plantillaRepository.findById(id).orElse(null);
        if (plantillaToUpdate != null) {
            plantillaToUpdate.setNombrePlantilla(plantilla.getNombrePlantilla());
            plantillaToUpdate.setColor(plantilla.getColor());
            plantillaToUpdate.setEnlace(plantilla.getEnlace());
            return plantillaRepository.save(plantillaToUpdate);
        } else {
            return null;
        }
    }

    public Plantilla patchPlantilla(Long id, Plantilla parcialPlantilla) {
        Plantilla plantillaToUpdate = plantillaRepository.findById(id).orElse(null);
        if (plantillaToUpdate != null) {
            if (parcialPlantilla.getNombrePlantilla() != null) {
                plantillaToUpdate.setNombrePlantilla(parcialPlantilla.getNombrePlantilla());
            }

            if (parcialPlantilla.getColor() != null) {
                plantillaToUpdate.setColor(parcialPlantilla.getColor());
            }

            if (parcialPlantilla.getEnlace() != null) {
                plantillaToUpdate.setEnlace(parcialPlantilla.getEnlace());
            }
            return plantillaRepository.save(plantillaToUpdate);
        } else {
            return null;
        }
    }

    public List<Plantilla> findByNombrePlantilla(String nombrePlantilla) {
        return plantillaRepository.findByNombrePlantilla(nombrePlantilla);
    }

}
