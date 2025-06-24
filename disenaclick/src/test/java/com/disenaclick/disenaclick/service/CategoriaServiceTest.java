package com.disenaclick.disenaclick.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.disenaclick.disenaclick.model.Categoria;
import com.disenaclick.disenaclick.repository.CategoriaRepository;

@SpringBootTest
public class CategoriaServiceTest {

    @Autowired
    private CategoriaService categoriaService;

    @MockBean
    private CategoriaRepository categoriaRepository;

    private Categoria createCategoria() {
        return new Categoria(1, "Negocio", "Administración", "/api/v1/categorias/administracion");// aquí se debe
                                                                                                  // agregar la cantidad
                                                                                                  // de categorías que
                                                                                                  // se desee
    }

    @Test
    public void testFindAll() {
        when(categoriaRepository.findAll()).thenReturn(List.of(createCategoria()));
        List<Categoria> categorias = categoriaService.findAll();
        assertNotNull(categorias);
        assertEquals(1, categorias.size());// el 1 representa la cantidad de categorías que se insertaron en la linea 27
    }

    @Test
    public void testFindById() {
        when(categoriaRepository.findById(1L)).thenReturn(java.util.Optional.of(createCategoria()));
        Categoria categoria = categoriaService.findById(1L);
        assertEquals("Administración", categoria.getNombreNegocio());
    }

    @Test
    public void testSave() {
        Categoria categoria = createCategoria();
        when(categoriaRepository.save(categoria)).thenReturn(categoria);
        Categoria savedCategoria = categoriaService.save(categoria);
        assertNotNull(savedCategoria);
        assertEquals("Administración", savedCategoria.getNombreNegocio());
    }

    @Test
    public void testPatchCategoria() {
        Categoria exitinCategoria = createCategoria();
        Categoria patchData = new Categoria();
        patchData.setNombreNegocio("Contabilidad");
        when(categoriaRepository.findById(1L)).thenReturn(java.util.Optional.of(exitinCategoria));
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(exitinCategoria);
        Categoria patchedCategoria = categoriaService.patchCategoria(1L, patchData);
        assertNotNull(patchedCategoria);
        assertEquals("Contabilidad", patchedCategoria.getNombreNegocio());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(categoriaRepository).deleteById(1L);
        categoriaService.delete(1L);// está definido como delete en el service
        verify(categoriaRepository, times(1)).deleteById(1L);
    }
}
