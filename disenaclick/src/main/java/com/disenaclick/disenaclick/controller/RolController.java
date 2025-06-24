package com.disenaclick.disenaclick.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.disenaclick.disenaclick.model.Rol;
import com.disenaclick.disenaclick.service.RolService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/roles")
@Tag(name = "Roles", description = "Operaciones CRUD para roles de usuarios")
public class RolController {

    @Autowired
    private RolService rolService;

    @Operation(summary = "Listar todos los roles")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado de roles obtenido correctamente"),
            @ApiResponse(responseCode = "204", description = "No hay roles disponibles")
    })
    @GetMapping
    public ResponseEntity<List<Rol>> listar() {
        List<Rol> rols = rolService.findAll();
        if (rols.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rols);
    }

    @Operation(summary = "Obtener un rol por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rol encontrado"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Rol> find(@PathVariable Long id) {
        Rol rol = rolService.findById(id);
        if (rol != null) {
            return ResponseEntity.ok(rol);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo rol")
    @ApiResponse(responseCode = "201", description = "Rol creado exitosamente")
    @PostMapping
    public ResponseEntity<Rol> save(@RequestBody Rol rol) {
        Rol rolNuevo = rolService.save(rol);
        return ResponseEntity.status(HttpStatus.CREATED).body(rolNuevo);
    }

    @Operation(summary = "Actualizar un rol existente (PUT)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rol actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Rol> actualizar(@PathVariable Long id, @RequestBody Rol rol) {
        Rol rolActualizado = rolService.updateRol(id, rol);
        if (rolActualizado != null) {
            return ResponseEntity.ok(rolActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Actualizar parcialmente un rol (PATCH)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rol actualizado parcialmente"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Rol> patchRol(@PathVariable Long id, @RequestBody Rol parcialRol) {
        Rol rolActualizado = rolService.patchRol(id, parcialRol);
        if (rolActualizado != null) {
            return ResponseEntity.ok(rolActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un rol por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Rol eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            rolService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
