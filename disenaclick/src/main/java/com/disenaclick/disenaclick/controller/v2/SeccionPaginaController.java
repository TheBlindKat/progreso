package com.disenaclick.disenaclick.controller.v2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.disenaclick.disenaclick.model.SeccionPagina;
import com.disenaclick.disenaclick.service.SeccionPaginaService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v2/secciones-paginas")
@Tag(name = "Sección de Página", description = "Operaciones CRUD para las secciones de las páginas")
public class SeccionPaginaController {

    @Autowired
    private SeccionPaginaService seccionPaginaService;

    @Operation(summary = "Listar todas las secciones de página")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente"),
            @ApiResponse(responseCode = "204", description = "No hay secciones disponibles")
    })
    @GetMapping
    public ResponseEntity<List<SeccionPagina>> listar() {
        List<SeccionPagina> seccionPaginas = seccionPaginaService.findAll();
        if (seccionPaginas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(seccionPaginas);
    }

    @Operation(summary = "Obtener una sección de página por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sección encontrada"),
            @ApiResponse(responseCode = "404", description = "Sección no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SeccionPagina> find(@PathVariable Long id) {
        SeccionPagina seccionPagina = seccionPaginaService.findById(id);
        if (seccionPagina != null) {
            return ResponseEntity.ok(seccionPagina);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear una nueva sección de página")
    @ApiResponse(responseCode = "201", description = "Sección creada correctamente")
    @PostMapping
    public ResponseEntity<SeccionPagina> save(@RequestBody SeccionPagina seccionPagina) {
        SeccionPagina seccionPaginaNueva = seccionPaginaService.save(seccionPagina);
        return ResponseEntity.status(HttpStatus.CREATED).body(seccionPaginaNueva);
    }

    @Operation(summary = "Actualizar una sección de página (PUT)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sección actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Sección no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SeccionPagina> actualizar(@PathVariable Long id, @RequestBody SeccionPagina seccionPagina) {
        SeccionPagina seccionPaginaActualizada = seccionPaginaService.updateSeccionPagina(id, seccionPagina);
        if (seccionPaginaActualizada != null) {
            return ResponseEntity.ok(seccionPaginaActualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Actualizar parcialmente una sección de página (PATCH)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sección parcialmente actualizada"),
            @ApiResponse(responseCode = "404", description = "Sección no encontrada")
    })
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

    @Operation(summary = "Eliminar una sección de página por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Sección eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Sección no encontrada")
    })
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
