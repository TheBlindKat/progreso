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

import com.disenaclick.disenaclick.assemblers.RolModelAssembler;
import com.disenaclick.disenaclick.model.Rol;
import com.disenaclick.disenaclick.service.RolService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v2/roles")
@Tag(name = "Roles", description = "Operaciones CRUD para roles de usuarios con HATEOAS")
public class RolControllerV2 {

    @Autowired
    private RolService rolService;

    @Autowired
    private RolModelAssembler rolAssembler;

    @Operation(summary = "Listar todos los roles")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado de roles obtenido correctamente"),
            @ApiResponse(responseCode = "204", description = "No hay roles disponibles")
    })
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Rol>>> listar() {
        List<Rol> roles = rolService.findAll();
        if (roles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<EntityModel<Rol>> rolesModel = roles.stream()
                .map(rolAssembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(rolesModel));
    }

    @Operation(summary = "Obtener un rol por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rol encontrado"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Rol>> find(@PathVariable Long id) {
        Rol rol = rolService.findById(id);
        if (rol != null) {
            return ResponseEntity.ok(rolAssembler.toModel(rol));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo rol")
    @ApiResponse(responseCode = "201", description = "Rol creado exitosamente")
    @PostMapping
    public ResponseEntity<EntityModel<Rol>> save(@RequestBody Rol rol) {
        Rol nuevoRol = rolService.save(rol);
        return ResponseEntity.status(HttpStatus.CREATED).body(rolAssembler.toModel(nuevoRol));
    }

    @Operation(summary = "Actualizar un rol existente (PUT)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rol actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Rol>> actualizar(@PathVariable Long id, @RequestBody Rol rol) {
        Rol rolActualizado = rolService.updateRol(id, rol);
        if (rolActualizado != null) {
            return ResponseEntity.ok(rolAssembler.toModel(rolActualizado));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Actualizar parcialmente un rol (PATCH)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rol actualizado parcialmente"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<EntityModel<Rol>> patchRol(@PathVariable Long id, @RequestBody Rol parcialRol) {
        Rol rolActualizado = rolService.patchRol(id, parcialRol);
        if (rolActualizado != null) {
            return ResponseEntity.ok(rolAssembler.toModel(rolActualizado));
        }
        return ResponseEntity.notFound().build();
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