package com.disenaclick.disenaclick.controller.v2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.disenaclick.disenaclick.model.Usuario;
import com.disenaclick.disenaclick.service.UsuarioService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/v2/usuarios")
@Tag(name = "Usuarios", description = "Operaciones CRUD para usuarios del sistema")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Listar todos los usuarios")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente"),
            @ApiResponse(responseCode = "204", description = "No hay usuarios disponibles")
    })
    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = usuarioService.findAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @Operation(summary = "Obtener un usuario por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> find(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo usuario")
    @ApiResponse(responseCode = "201", description = "Usuario creado correctamente")
    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
        Usuario usuarioNuevo = usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioNuevo);
    }

    @Operation(summary = "Actualizar un usuario (PUT)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario usuarioActualizado = usuarioService.updateUsuario(id, usuario);
        if (usuarioActualizado != null) {
            return ResponseEntity.ok(usuarioActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Actualizar parcialmente un usuario (PATCH)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario parcialmente actualizado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> patchUsuario(@PathVariable Long id, @RequestBody Usuario parcialUsuario) {
        Usuario usuarioActualizado = usuarioService.patchUsuario(id, parcialUsuario);
        if (usuarioActualizado != null) {
            return ResponseEntity.ok(usuarioActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un usuario por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuario eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            usuarioService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Obtener usuarios con su rol y plantilla")
    @ApiResponse(responseCode = "200", description = "Datos obtenidos correctamente")
    @GetMapping("/rol-plantilla")
    public ResponseEntity<List<Object[]>> getUsuariosConRolYPlantilla() {
        List<Object[]> resultados = usuarioService.obtenerUsuariosConRolYPlantilla();
        return ResponseEntity.ok(resultados);
    }

    @Operation(summary = "Obtener usuarios con más información relacionada")
    @ApiResponse(responseCode = "200", description = "Datos obtenidos correctamente")
    @GetMapping("/mas-info")
    public ResponseEntity<List<Object[]>> getUsuariosConInfoExtra() {
        List<Object[]> resultados = usuarioService.obtenerUsuariosConMasInfo();
        return ResponseEntity.ok(resultados);
    }
}
