package com.disenaclick.disenaclick.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.disenaclick.disenaclick.model.RolUsuario;
import com.disenaclick.disenaclick.service.RolUsuarioService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/rol_usuarios")
@Tag(name = "RolUsuario", description = "Operaciones CRUD para roles de usuarios internos")
public class RolUsuarioController {

    @Autowired
    private RolUsuarioService rolUsuarioService;

    @Operation(summary = "Listar todos los roles de usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente"),
            @ApiResponse(responseCode = "204", description = "No hay roles disponibles")
    })
    @GetMapping
    public ResponseEntity<List<RolUsuario>> listar() {
        List<RolUsuario> rolsUsuarios = rolUsuarioService.findAll();
        if (rolsUsuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rolsUsuarios);
    }

    @Operation(summary = "Obtener un rol de usuario por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rol encontrado"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RolUsuario> find(@PathVariable Long id) {
        RolUsuario rolUsuario = rolUsuarioService.findById(id);
        if (rolUsuario != null) {
            return ResponseEntity.ok(rolUsuario);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo rol de usuario")
    @ApiResponse(responseCode = "201", description = "Rol creado correctamente")
    @PostMapping
    public ResponseEntity<RolUsuario> save(@RequestBody RolUsuario rolUsuario) {
        RolUsuario rolUsunuevo = rolUsuarioService.save(rolUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(rolUsunuevo);
    }

    @Operation(summary = "Actualizar un rol de usuario (PUT)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rol actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<RolUsuario> actualizar(@PathVariable Long id, @RequestBody RolUsuario rolUsuario) {
        RolUsuario rolusuarioActualizado = rolUsuarioService.updateRolUsuario(id, rolUsuario);
        if (rolusuarioActualizado != null) {
            return ResponseEntity.ok(rolusuarioActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Actualizar parcialmente un rol de usuario (PATCH)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rol actualizado parcialmente"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<RolUsuario> patchRolUsuario(@PathVariable Long id,
            @RequestBody RolUsuario parcialRolUsuario) {
        RolUsuario rolusuarioActualizado = rolUsuarioService.patchRolUsuario(id, parcialRolUsuario);
        if (rolusuarioActualizado != null) {
            return ResponseEntity.ok(rolusuarioActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un rol de usuario por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Rol eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            rolUsuarioService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
