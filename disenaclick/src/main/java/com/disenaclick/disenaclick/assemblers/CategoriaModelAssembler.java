package com.disenaclick.disenaclick.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.disenaclick.disenaclick.controller.v2.CategoriaControllerV2;
import com.disenaclick.disenaclick.model.Categoria;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CategoriaModelAssembler implements RepresentationModelAssembler<Categoria, EntityModel<Categoria>> {

    @SuppressWarnings("null")
    @Override
    public EntityModel<Categoria> toModel(Categoria categoria) {
        return EntityModel.of(categoria,
                linkTo(methodOn(CategoriaControllerV2.class).find(categoria.getId().longValue())).withSelfRel(),
                linkTo(methodOn(CategoriaControllerV2.class).listar()).withRel("categorias"),
                linkTo(methodOn(CategoriaControllerV2.class).actualizar(categoria.getId().longValue(), categoria))
                        .withRel("actualizar"),
                linkTo(methodOn(CategoriaControllerV2.class).patchCategoria(categoria.getId().longValue(), categoria))
                        .withRel("actualizar-parcial"),
                linkTo(methodOn(CategoriaControllerV2.class).delete(categoria.getId().longValue()))
                        .withRel("eliminar"));
    }

}
