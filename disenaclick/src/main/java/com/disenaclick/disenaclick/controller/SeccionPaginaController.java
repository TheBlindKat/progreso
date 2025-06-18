package com.disenaclick.disenaclick.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.disenaclick.disenaclick.model.SeccionPagina;
import com.disenaclick.disenaclick.service.SeccionPaginaService;

@RestController
@RequestMapping("/api/v1/secciones-paginas")
public class SeccionPaginaController {

    @Autowired
    private SeccionPaginaService seccionPaginaService;

    @GetMapping
    public ResponseEntity<List<SeccionPagina>> listar() {
        List<SeccionPagina> seccionPaginas = seccionPaginaService.findAll();
        if (seccionPaginas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(seccionPaginas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeccionPagina> find(@PathVariable Long id) {
        SeccionPagina seccionPagina = seccionPaginaService.findById(id);
        if (seccionPagina != null) {
            return ResponseEntity.ok(seccionPagina);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<SeccionPagina> save(@RequestBody SeccionPagina seccionPagina) {
        SeccionPagina seccionPaginaNueva = seccionPaginaService.save(seccionPagina);
        return ResponseEntity.status(HttpStatus.CREATED).body(seccionPaginaNueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeccionPagina> actualizar(@PathVariable Long id, @RequestBody SeccionPagina seccionPagina) {
        SeccionPagina seccionPaginaActualizada = seccionPaginaService.updateSeccionPagina(id, seccionPagina);
        if (seccionPaginaActualizada != null) {
            return ResponseEntity.ok(seccionPaginaActualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SeccionPagina> patchSeccionPagina(@PathVariable Long id,
            @RequestBody SeccionPagina parcialSeccionPagina) {
        SeccionPagina seccionPaginaActualizada = seccionPaginaService.patchSeccionPagina(id, parcialSeccionPagina);
        if (seccionPaginaActualizada != null) {
            return ResponseEntity.ok(seccionPaginaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            seccionPaginaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
