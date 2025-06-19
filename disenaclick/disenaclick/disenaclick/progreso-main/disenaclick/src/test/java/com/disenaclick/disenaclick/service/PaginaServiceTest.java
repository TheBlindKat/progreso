package com.disenaclick.disenaclick.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.disenaclick.disenaclick.model.Pagina;
import com.disenaclick.disenaclick.model.Plantilla;
import com.disenaclick.disenaclick.model.Usuario;
import com.disenaclick.disenaclick.repository.PaginaRepository;

@SpringBootTest
public class PaginaServiceTest {

    @Autowired
    private PaginaService paginaService;

    @MockBean
    private PaginaRepository paginaRepository;

    private Pagina createPagina() {
        return new Pagina(
                1,
                "mantra",
                LocalDate.now(),
                new Usuario(),
                new Plantilla());
    }

    @Test
    public void testFindAll() {
        when(paginaRepository.findAll()).thenReturn(List.of(createPagina()));
        List<Pagina> paginas = paginaService.findAll();
        assertNotNull(paginas);
        assertEquals(1, paginas.size());
        assertEquals("mantra", paginas.get(0).getNombrePagina());
    }
    @Test

    public void testFindById() {
        when(paginaRepository.findById(1L)).thenReturn(java.util.Optional.of(createPagina()));
        Pagina pagina = paginaService.findById(1L);
        assertNotNull(pagina);
        assertEquals("mantra", pagina.getNombrePagina());
    }
    @Test

    public void testSave() {
        Pagina pagina = createPagina();
        when(paginaRepository.save(pagina)).thenReturn(pagina);
        Pagina savedPagina = paginaService.save(pagina);
        assertNotNull(savedPagina);
        assertEquals("mantra", savedPagina.getNombrePagina());
    }

    @Test
    public void testPatchPagina() {
        Pagina existingPagina = createPagina();
        Pagina patchData = new Pagina();
        patchData.setNombrePagina("mantra Actualizado");
        when(paginaRepository.findById(1L)).thenReturn(java.util.Optional.of(existingPagina));
        when(paginaRepository.save(any(Pagina.class))).thenReturn(existingPagina);
        Pagina patchedPagina = paginaService.patchPagina(1L, patchData);
        assertNotNull(patchedPagina);
        assertEquals("mantra Actualizado", patchedPagina.getNombrePagina());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(paginaRepository).deleteById(1L);
        paginaService.delete(1L);
        verify(paginaRepository, times(1)).deleteById(1L);
    }



}




