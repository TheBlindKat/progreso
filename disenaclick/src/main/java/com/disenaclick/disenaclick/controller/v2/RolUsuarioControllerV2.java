package com.disenaclick.disenaclick.controller.v2;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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

import com.disenaclick.disenaclick.assemblers.RolUsuarioModelAssembler;
import com.disenaclick.disenaclick.model.RolUsuario;
import com.disenaclick.disenaclick.service.RolUsuarioService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v2/rol_usuarios")
@Tag(name = "RolUsuario", description = "Operaciones CRUD para roles de usuarios internos - V2 con HATEOAS")
public class RolUsuarioControllerV2 {

    @Autowired
    private RolUsuarioService rolUsuarioService;

    @Autowired
    private RolUsuarioModelAssembler rolUsuarioModelAssembler;

    @Operation(summary = "Listar todos los roles de usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente"),
            @ApiResponse(responseCode = "204", description = "No hay roles disponibles")
    })
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<RolUsuario>>> listar() {
        List<RolUsuario> roles = rolUsuarioService.findAll();
        if (roles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<EntityModel<RolUsuario>> rolesModel = roles.stream()
                .map(rolUsuarioModelAssembler::toModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(rolesModel));
    }

    @Operation(summary = "Obtener un rol de usuario por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rol encontrado"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<RolUsuario>> find(@PathVariable Long id) {
        RolUsuario rolUsuario = rolUsuarioService.findById(id);
        if (rolUsuario != null) {
            return ResponseEntity.ok(rolUsuarioModelAssembler.toModel(rolUsuario));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo rol de usuario")
    @ApiResponse(responseCode = "201", description = "Rol creado correctamente")
    @PostMapping
    public ResponseEntity<EntityModel<RolUsuario>> save(@RequestBody RolUsuario rolUsuario) {
        RolUsuario nuevoRol = rolUsuarioService.save(rolUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(rolUsuarioModelAssembler.toModel(nuevoRol));
    }

    @Operation(summary = "Actualizar un rol de usuario (PUT)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rol actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<RolUsuario>> actualizar(@PathVariable Long id,
            @RequestBody RolUsuario rolUsuario) {
        RolUsuario rolActualizado = rolUsuarioService.updateRolUsuario(id, rolUsuario);
        if (rolActualizado != null) {
            return ResponseEntity.ok(rolUsuarioModelAssembler.toModel(rolActualizado));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Actualizar parcialmente un rol de usuario (PATCH)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rol actualizado parcialmente"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<EntityModel<RolUsuario>> patchRolUsuario(@PathVariable Long id,
            @RequestBody RolUsuario parcialRolUsuario) {
        RolUsuario rolActualizado = rolUsuarioService.patchRolUsuario(id, parcialRolUsuario);
        if (rolActualizado != null) {
            return ResponseEntity.ok(rolUsuarioModelAssembler.toModel(rolActualizado));
        }
        return ResponseEntity.notFound().build();
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