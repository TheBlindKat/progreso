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

import com.disenaclick.disenaclick.model.RolUsuario;
import com.disenaclick.disenaclick.service.RolUsuarioService;

@RestController
@RequestMapping("/api/v1/roles-usuarios")
public class RolUsuarioController {

    @Autowired
    private RolUsuarioService rolUsuarioService;

    @GetMapping
    public ResponseEntity<List<RolUsuario>> listar() {
        List<RolUsuario> rolesUsuarios = rolUsuarioService.findAll();
        if (rolesUsuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rolesUsuarios);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<RolUsuario> find(@PathVariable Long id) {
        RolUsuario rolUsuario = rolUsuarioService.findById(id);
        if (rolUsuario != null) {
            return ResponseEntity.ok(rolUsuario);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<RolUsuario> save(@RequestBody RolUsuario rolUsuario) {
        RolUsuario rolUsuarioNuevo = rolUsuarioService.save(rolUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(rolUsuarioNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolUsuario> actualizar(@PathVariable Long id, @RequestBody RolUsuario rolUsuario) {
        RolUsuario rolUsuarioActualizado = rolUsuarioService.updateRolUsuario(id, rolUsuario);
        if (rolUsuarioActualizado != null) {
            return ResponseEntity.ok(rolUsuarioActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RolUsuario> patchRol(@PathVariable Long id, @RequestBody RolUsuario rolUsuario) {
        RolUsuario rolUsuarioActualizado = rolUsuarioService.patchRolUsuario(id, rolUsuario);
        if (rolUsuarioActualizado != null) {
            return ResponseEntity.ok(rolUsuarioActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

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
