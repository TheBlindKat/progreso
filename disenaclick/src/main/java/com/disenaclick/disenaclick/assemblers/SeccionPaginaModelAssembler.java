package com.disenaclick.disenaclick.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.disenaclick.disenaclick.controller.v2.SeccionPaginaControllerV2;
import com.disenaclick.disenaclick.model.SeccionPagina;

@Component
public class SeccionPaginaModelAssembler
        implements RepresentationModelAssembler<SeccionPagina, EntityModel<SeccionPagina>> {

    @SuppressWarnings("null")
    @Override
    public EntityModel<SeccionPagina> toModel(SeccionPagina seccionPagina) {
        return EntityModel.of(seccionPagina,
                linkTo(methodOn(SeccionPaginaControllerV2.class).find(seccionPagina.getId().longValue())).withSelfRel(),
                linkTo(methodOn(SeccionPaginaControllerV2.class).listar()).withRel("secciones-paginas"),
                linkTo(methodOn(SeccionPaginaControllerV2.class).actualizar(seccionPagina.getId().longValue(),
                        seccionPagina)).withRel("actualizar"),
                linkTo(methodOn(SeccionPaginaControllerV2.class).patchSeccionPagina(seccionPagina.getId().longValue(),
                        seccionPagina)).withRel("actualizar-parcial"),
                linkTo(methodOn(SeccionPaginaControllerV2.class).delete(seccionPagina.getId().longValue()))
                        .withRel("eliminar"));
    }
}
