package com.disenaclick.disenaclick.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.disenaclick.disenaclick.model.Plantilla;

@Repository
public interface PlantillaRepository extends JpaRepository<Plantilla, Long> {

    List<Plantilla> findByNombrePlantilla(String nombrePlantilla);

    List<Plantilla> findById(Integer id);

}
