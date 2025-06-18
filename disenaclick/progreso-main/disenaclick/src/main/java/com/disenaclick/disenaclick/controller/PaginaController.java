package com.disenaclick.disenaclick.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.disenaclick.disenaclick.model.Pagina;
import com.disenaclick.disenaclick.service.PaginaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/paginas")
public class PaginaController {

    @Autowired
    private PaginaService paginaService;

    @GetMapping
    public ResponseEntity<List<Pagina>> listar() {
        List<Pagina> paginas = paginaService.findAll();
        if (paginas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(paginas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagina> find(@PathVariable Long id) {
        Pagina pagina = paginaService.findById(id);
        if (pagina != null) {
            return ResponseEntity.ok(pagina);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Pagina>> buscar(@RequestParam String nombre) {
        List<Pagina> paginas = paginaService.findByNombrePagina(nombre);
        if (paginas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(paginas);
    }

    @GetMapping("/usuario")
    public List<Object[]> obtenerPaginasConUsuario() {
        return paginaService.obtenerUsuarioPaginas();
    }

    @PostMapping
    public ResponseEntity<Pagina> save(@RequestBody Pagina pagina) {
        Pagina paginaNueva = paginaService.save(pagina);
        return ResponseEntity.status(HttpStatus.CREATED).body(paginaNueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pagina> actualizar(@PathVariable Long id, @RequestBody Pagina pagina) {
        Pagina paginaActualizada = paginaService.updatePagina(id, pagina);
        if (paginaActualizada != null) {
            return ResponseEntity.ok(paginaActualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Pagina> patchPagina(@PathVariable Long id, @RequestBody Pagina parcialPagina) {
        Pagina paginaActualizada = paginaService.patchPagina(id, parcialPagina);
        if (paginaActualizada != null) {
            return ResponseEntity.ok(paginaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            paginaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
