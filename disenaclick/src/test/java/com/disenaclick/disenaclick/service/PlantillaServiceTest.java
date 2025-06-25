package com.disenaclick.disenaclick.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.disenaclick.disenaclick.model.Plantilla;
import com.disenaclick.disenaclick.repository.PlantillaRepository;


@SpringBootTest
public class PlantillaServiceTest {


    @Autowired
    private PlantillaService plantillaService;


    @MockBean
    private PlantillaRepository plantillaRepository;


    private Plantilla createPlantilla(){
        return new Plantilla(   1,
                                "Plantilla prueba",
                                "rojo",
                                "http://enlace.com");
    }


    @Test
    public void testFindAll(){
        when(plantillaRepository.findAll()).thenReturn(List.of(createPlantilla()));
        List<Plantilla> plantillas = plantillaService.findAll();
        assertNotNull(plantillas);
        assertEquals(1, plantillas.size());
    }


    @Test
    public void testFindById(){
        when(plantillaRepository.findById(1L)).thenReturn(java.util.Optional.of(createPlantilla()));
        Plantilla plantilla = plantillaService.findById(1L);
        assertNotNull(plantilla);
        assertEquals("Plantilla prueba", plantilla.getNombrePlantilla());
    }


    @Test
    public void testSave(){
        Plantilla plantilla = createPlantilla();
        when(plantillaRepository.save(plantilla)).thenReturn(plantilla);
        Plantilla savedPlantilla = plantillaService.save(plantilla);
        assertNotNull(savedPlantilla);
        assertEquals("Plantilla prueba", savedPlantilla.getNombrePlantilla());
    }


    @Test
    public void testPatchPlantilla(){
        Plantilla exitingPlantilla = createPlantilla();
        Plantilla patchData = new Plantilla();
        patchData.setNombrePlantilla("Plantilla para testeo");


        when(plantillaRepository.findById(1L)).thenReturn(java.util.Optional.of(exitingPlantilla));
        when(plantillaRepository.save(any(Plantilla.class))).thenReturn(patchData);


        Plantilla patchedPlantilla = plantillaService.patchPlantilla(1L, patchData);
        assertNotNull(patchedPlantilla);
        assertEquals("Plantilla para testeo", patchedPlantilla.getNombrePlantilla());
    }


    @Test
    public void testDeleteById(){
        doNothing().when(plantillaRepository).deleteById(1L);
        plantillaService.delete(1L);
        verify(plantillaRepository, times(1)).deleteById(1L);
    }
}
