package com.disenaclick.disenaclick.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.disenaclick.disenaclick.model.SeccionPagina;
import com.disenaclick.disenaclick.repository.SeccionPaginaRepository;

@SpringBootTest
public class SeccionPaginaServiceTest {

    @Autowired
    private SeccionPaginaService seccionPaginaService;

    @MockBean
    private SeccionPaginaRepository seccionPaginaRepository;

    private SeccionPagina createSeccionPagina() {
        return new SeccionPagina(1, "prueba 3", "test seccion pagina");
    }

    @Test
    public void testFindAll() {
        when(seccionPaginaRepository.findAll()).thenReturn(List.of(createSeccionPagina()));
        List<SeccionPagina> seccionPaginas = seccionPaginaService.findAll();
        assertNotNull(seccionPaginas);
        assertEquals(1, seccionPaginas.size());
    }

    @Test
    public void testFindById() {
        when(seccionPaginaRepository.findById(1L)).thenReturn(Optional.of(createSeccionPagina()));
        SeccionPagina seccionPagina = seccionPaginaService.findById(1L);
        assertNotNull(seccionPagina);
        assertEquals("seccion 1", seccionPagina.getTitulo());
    }

    @Test
    public void testSave() {
        SeccionPagina seccionPagina = createSeccionPagina();
        when(seccionPaginaRepository.save(seccionPagina)).thenReturn(seccionPagina);
        SeccionPagina savedSeccionPagina = seccionPaginaService.save(seccionPagina);
        assertNotNull(savedSeccionPagina);
        assertEquals("seccion 1", savedSeccionPagina.getId());
    }

    @Test
    public void testPatchSeeccionPagina() {
        SeccionPagina existingSeccionPagina = createSeccionPagina();
        SeccionPagina patchData = new SeccionPagina();
        patchData.setTitulo(" prueba 4 ");
        when(seccionPaginaRepository.findById(1L)).thenReturn(Optional.of(existingSeccionPagina));
        when(seccionPaginaRepository.save(any(SeccionPagina.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        SeccionPagina updatedSeccionPagina = seccionPaginaService.patchSeccionPagina(1L, patchData);
        assertNotNull(updatedSeccionPagina);
        assertEquals("prueba 3 actualizada", updatedSeccionPagina.getTitulo());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(seccionPaginaRepository).deleteById(1L);
        seccionPaginaService.delete(1L);
        verify(seccionPaginaRepository, times(1)).deleteById(1L);
    }

}
