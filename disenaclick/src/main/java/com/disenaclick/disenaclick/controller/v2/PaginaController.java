package com.disenaclick.disenaclick.controller.v2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.disenaclick.disenaclick.model.Pagina;
import com.disenaclick.disenaclick.service.PaginaService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v2/paginas")
@Tag(name = "Páginas", description = "Operaciones relacionadas con la entidad Página")
public class PaginaController {

    @Autowired
    private PaginaService paginaService;

    @Operation(summary = "Listar todas las páginas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado exitoso"),
            @ApiResponse(responseCode = "204", description = "No hay páginas registradas")
    })
    @GetMapping
    public ResponseEntity<List<Pagina>> listar() {
        List<Pagina> paginas = paginaService.findAll();
        if (paginas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(paginas);
    }

    @Operation(summary = "Obtener una página por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Página encontrada"),
            @ApiResponse(responseCode = "404", description = "Página no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Pagina> find(@PathVariable Long id) {
        Pagina pagina = paginaService.findById(id);
        if (pagina != null) {
            return ResponseEntity.ok(pagina);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Buscar páginas por nombre")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Resultados encontrados"),
            @ApiResponse(responseCode = "204", description = "Sin coincidencias")
    })
    @GetMapping("/buscar")
    public ResponseEntity<List<Pagina>> buscar(@RequestParam String nombre) {
        List<Pagina> paginas = paginaService.findByNombrePagina(nombre);
        if (paginas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(paginas);
    }

    @Operation(summary = "Obtener páginas junto con información de usuario")
    @ApiResponse(responseCode = "200", description = "Consulta exitosa")
    @GetMapping("/usuario")
    public List<Object[]> obtenerPaginasConUsuario() {
        return paginaService.obtenerUsuarioPaginas();
    }

    @Operation(summary = "Crear una nueva página")
    @ApiResponse(responseCode = "201", description = "Página creada exitosamente")
    @PostMapping
    public ResponseEntity<Pagina> save(@RequestBody Pagina pagina) {
        Pagina paginaNueva = paginaService.save(pagina);
        return ResponseEntity.status(HttpStatus.CREATED).body(paginaNueva);
    }

    @Operation(summary = "Actualizar completamente una página")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Página actualizada"),
            @ApiResponse(responseCode = "404", description = "Página no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Pagina> actualizar(@PathVariable Long id, @RequestBody Pagina pagina) {
        Pagina paginaActualizada = paginaService.updatePagina(id, pagina);
        if (paginaActualizada != null) {
            return ResponseEntity.ok(paginaActualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Actualizar parcialmente una página")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Página actualizada parcialmente"),
            @ApiResponse(responseCode = "404", description = "Página no encontrada")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Pagina> patchPagina(@PathVariable Long id, @RequestBody Pagina parcialPagina) {
        Pagina paginaActualizada = paginaService.patchPagina(id, parcialPagina);
        if (paginaActualizada != null) {
            return ResponseEntity.ok(paginaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar una página")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Página eliminada"),
            @ApiResponse(responseCode = "404", description = "Página no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            paginaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
