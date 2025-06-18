package com.disenaclick.disenaclick.service;

import static org.junit.jupiter.api.Assertions.*;
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

}
