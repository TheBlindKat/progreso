package com.disenaclick.disenaclick.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.disenaclick.disenaclick.model.Plantilla;
import com.disenaclick.disenaclick.service.PlantillaService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/plantillas")
@Tag(name = "Plantillas", description = "Operaciones CRUD para gestionar plantillas")
public class PlantillaController {

    @Autowired
    private PlantillaService plantillaService;

    @Operation(summary = "Listar todas las plantillas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado de plantillas obtenido correctamente"),
            @ApiResponse(responseCode = "204", description = "No existen plantillas")
    })
    @GetMapping
    public ResponseEntity<List<Plantilla>> listar() {
        List<Plantilla> plantillas = plantillaService.findAll();
        if (plantillas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(plantillas);
    }

    @Operation(summary = "Buscar plantilla por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Plantilla encontrada"),
            @ApiResponse(responseCode = "404", description = "Plantilla no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Plantilla> find(@PathVariable Long id) {
        Plantilla plantilla = plantillaService.findById(id);
        if (plantilla != null) {
            return ResponseEntity.ok(plantilla);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear nueva plantilla")
    @ApiResponse(responseCode = "201", description = "Plantilla creada correctamente")
    @PostMapping
    public ResponseEntity<Plantilla> save(@RequestBody Plantilla plantilla) {
        Plantilla plantillaNueva = plantillaService.save(plantilla);
        return ResponseEntity.status(HttpStatus.CREATED).body(plantillaNueva);
    }

    @Operation(summary = "Actualizar plantilla por ID (PUT)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Plantilla actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Plantilla no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Plantilla> actualizar(@PathVariable Long id, @RequestBody Plantilla plantilla) {
        Plantilla plantillaActualizada = plantillaService.updatePlantilla(id, plantilla);
        if (plantillaActualizada != null) {
            return ResponseEntity.ok(plantillaActualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Actualizar parcialmente una plantilla (PATCH)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Plantilla actualizada parcialmente"),
            @ApiResponse(responseCode = "404", description = "Plantilla no encontrada")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Plantilla> patchPlantilla(@PathVariable Long id, @RequestBody Plantilla parcialplantilla) {
        Plantilla plantillaActualizada = plantillaService.patchPlantilla(id, parcialplantilla);
        if (plantillaActualizada != null) {
            return ResponseEntity.ok(plantillaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar plantilla por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Plantilla eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Plantilla no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            plantillaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
