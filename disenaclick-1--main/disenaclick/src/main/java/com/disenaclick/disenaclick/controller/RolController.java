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

import com.disenaclick.disenaclick.model.Rol;
import com.disenaclick.disenaclick.service.RolService;

@RestController
@RequestMapping("/api/v1/roless")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping
    public ResponseEntity<List<Rol>> listar() {
        List<Rol> rols = rolService.findAll();
        if (rols.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rols);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> find(@PathVariable Long id) {
        Rol rol = rolService.findById(id);
        if (rol != null) {
            return ResponseEntity.ok(rol);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Rol> save(@RequestBody Rol rol) {
        Rol rolNuevo = rolService.save(rol);
        return ResponseEntity.status(HttpStatus.CREATED).body(rolNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> actualizar(@PathVariable Long id, @RequestBody Rol rol) {
        Rol rolActualizado = rolService.updateRol(id, rol);
        if (rolActualizado != null) {
            return ResponseEntity.ok(rolActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Rol> patchRol(@PathVariable Long id, @RequestBody Rol parcialRol) {
        Rol rolActualizado = rolService.patchRol(id, parcialRol);
        if (rolActualizado != null) {
            return ResponseEntity.ok(rolActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

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
