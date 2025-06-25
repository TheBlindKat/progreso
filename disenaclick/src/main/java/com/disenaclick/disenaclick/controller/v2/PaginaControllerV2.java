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

import com.disenaclick.disenaclick.assemblers.PaginaModelAssembler;
import com.disenaclick.disenaclick.model.Pagina;
import com.disenaclick.disenaclick.service.PaginaService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v2/paginas")
@Tag(name = "Páginas", description = "Operaciones relacionadas con la entidad Página con HATEOAS")
public class PaginaControllerV2 {

    @Autowired
    private PaginaService paginaService;

    @Autowired
    private PaginaModelAssembler paginaAssembler;

    @Operation(summary = "Listar todas las páginas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado exitoso"),
            @ApiResponse(responseCode = "204", description = "No hay páginas registradas")
    })
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Pagina>>> listar() {
        List<Pagina> paginas = paginaService.findAll();
        if (paginas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<EntityModel<Pagina>> paginaModels = paginas.stream()
                .map(paginaAssembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(paginaModels));
    }

    @Operation(summary = "Obtener una página por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Página encontrada"),
            @ApiResponse(responseCode = "404", description = "Página no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Pagina>> find(@PathVariable Long id) {
        Pagina pagina = paginaService.findById(id);
        if (pagina != null) {
            return ResponseEntity.ok(paginaAssembler.toModel(pagina));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear una nueva página")
    @ApiResponse(responseCode = "201", description = "Página creada exitosamente")
    @PostMapping
    public ResponseEntity<EntityModel<Pagina>> save(@RequestBody Pagina pagina) {
        Pagina paginaNueva = paginaService.save(pagina);
        return ResponseEntity.status(HttpStatus.CREATED).body(paginaAssembler.toModel(paginaNueva));
    }

    @Operation(summary = "Actualizar completamente una página")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Página actualizada"),
            @ApiResponse(responseCode = "404", description = "Página no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Pagina>> actualizar(@PathVariable Long id, @RequestBody Pagina pagina) {
        Pagina paginaActualizada = paginaService.updatePagina(id, pagina);
        if (paginaActualizada != null) {
            return ResponseEntity.ok(paginaAssembler.toModel(paginaActualizada));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Actualizar parcialmente una página")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Página actualizada parcialmente"),
            @ApiResponse(responseCode = "404", description = "Página no encontrada")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<EntityModel<Pagina>> patchPagina(@PathVariable Long id, @RequestBody Pagina parcialPagina) {
        Pagina paginaActualizada = paginaService.patchPagina(id, parcialPagina);
        if (paginaActualizada != null) {
            return ResponseEntity.ok(paginaAssembler.toModel(paginaActualizada));
        }
        return ResponseEntity.notFound().build();
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