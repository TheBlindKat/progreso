package com.disenaclick.disenaclick.controller.v2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.disenaclick.disenaclick.model.Categoria;
import com.disenaclick.disenaclick.service.CategoriaService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/v2/categorias")
@Tag(name = "Categorías", description = "Operaciones relacionadas con categorías de negocios")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Operation(summary = "Listar todas las categorías")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {
        List<Categoria> categorias = categoriaService.findAll();
        if (categorias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categorias);
    }

    @Operation(summary = "Obtener una categoría por su ID")
    @ApiResponse(responseCode = "200", description = "Categoría encontrada", content = @Content(schema = @Schema(implementation = Categoria.class)))
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> find(@PathVariable Long id) {
        try {
            Categoria categoria = categoriaService.findById(id);
            return ResponseEntity.ok(categoria);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Crear una nueva categoría")
    @ApiResponse(responseCode = "201", description = "Categoría creada exitosamente")
    @PostMapping
    public ResponseEntity<Categoria> save(@RequestBody Categoria categoria) {
        Categoria categoriaNueva = categoriaService.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaNueva);
    }

    @Operation(summary = "Actualizar una categoría por su ID")
    @ApiResponse(responseCode = "200", description = "Categoría actualizada")
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizar(@PathVariable Long id, @RequestBody Categoria categoria) {
        try {
            Categoria categoriaActualizada = categoriaService.updateCategoria(id, categoria);
            return ResponseEntity.ok(categoriaActualizada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Aplicar cambios parciales a una categoría")
    @ApiResponse(responseCode = "200", description = "Categoría actualizada parcialmente")
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    @PatchMapping("/{id}")
    public ResponseEntity<Categoria> patchCategoria(@PathVariable Long id, @RequestBody Categoria parcialCategoria) {
        Categoria categoriaActualizada = categoriaService.patchCategoria(id, parcialCategoria);
        if (categoriaActualizada != null) {
            return ResponseEntity.ok(categoriaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar una categoría por ID")
    @ApiResponse(responseCode = "204", description = "Categoría eliminada")
    @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            categoriaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}

/*
 * @RestController
 * 
 * @RequestMapping("/api/v1/categorias")
 * public class CategoriaController {
 * 
 * @Autowired
 * private CategoriaService categoriaService;
 * 
 * @GetMapping
 * public ResponseEntity<List<Categoria>> listar() {
 * List<Categoria> categorias = categoriaService.findAll();
 * if (categorias.isEmpty()) {
 * return ResponseEntity.noContent().build();
 * }
 * return ResponseEntity.ok(categorias);
 * }
 * 
 * @GetMapping("/{id}")
 * public ResponseEntity<Categoria> find(@PathVariable Long id) {
 * try {
 * Categoria categoria = categoriaService.findById(id);
 * return ResponseEntity.ok(categoria);
 * } catch (Exception e) {
 * return ResponseEntity.notFound().build();
 * }
 * }
 * 
 * @PostMapping
 * public ResponseEntity<Categoria> save(@RequestBody Categoria categoria) {
 * Categoria categoriaNueva = categoriaService.save(categoria);
 * return ResponseEntity.status(HttpStatus.CREATED).body(categoriaNueva);
 * }
 * 
 * @PutMapping("/{id}")
 * public ResponseEntity<Categoria> actualizar(@PathVariable Long
 * id, @RequestBody Categoria categoria) {
 * try {
 * Categoria categoriaActualizada = categoriaService.updateCategoria(id,
 * categoria);
 * return ResponseEntity.ok(categoriaActualizada);
 * } catch (Exception e) {
 * return ResponseEntity.notFound().build();
 * }
 * 
 * }
 * 
 * @PatchMapping("/{id}")
 * public ResponseEntity<Categoria> patchCategoria(@PathVariable Long
 * id, @RequestBody Categoria parcialCategoria) {
 * Categoria categoriaActualizada = categoriaService.patchCategoria(id,
 * parcialCategoria);
 * if (categoriaActualizada != null) {
 * return ResponseEntity.ok(categoriaActualizada);
 * } else {
 * return ResponseEntity.notFound().build();
 * }
 * 
 * }
 * 
 * @DeleteMapping("/{id}")
 * public ResponseEntity<Void> delete(@PathVariable Long id) {
 * try {
 * categoriaService.delete(id);
 * return ResponseEntity.noContent().build();
 * } catch (Exception e) {
 * return ResponseEntity.notFound().build();
 * }
 * }
 * 
 * }
 */