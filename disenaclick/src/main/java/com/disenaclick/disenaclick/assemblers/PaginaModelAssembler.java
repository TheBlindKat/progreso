package com.disenaclick.disenaclick.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.disenaclick.disenaclick.controller.v2.PaginaControllerV2;
import com.disenaclick.disenaclick.model.Pagina;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PaginaModelAssembler implements RepresentationModelAssembler<Pagina, EntityModel<Pagina>> {

    @SuppressWarnings("null")
    @Override
    public EntityModel<Pagina> toModel(Pagina pagina) {
        return EntityModel.of(pagina,
                linkTo(methodOn(PaginaControllerV2.class).find(pagina.getId().longValue())).withSelfRel(),
                linkTo(methodOn(PaginaControllerV2.class).listar()).withRel("paginas"),
                linkTo(methodOn(PaginaControllerV2.class).actualizar(pagina.getId().longValue(), pagina))
                        .withRel("actualizar"),
                linkTo(methodOn(PaginaControllerV2.class).patchPagina(pagina.getId().longValue(), pagina))
                        .withRel("actualizar-parcial"),
                linkTo(methodOn(PaginaControllerV2.class).delete(pagina.getId().longValue())).withRel("eliminar"));
    }
}