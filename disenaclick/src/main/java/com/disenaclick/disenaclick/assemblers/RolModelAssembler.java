package com.disenaclick.disenaclick.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import com.disenaclick.disenaclick.controller.v2.RolControllerV2;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.disenaclick.disenaclick.model.Rol;

@Component
public class RolModelAssembler implements RepresentationModelAssembler<Rol, EntityModel<Rol>> {
    @SuppressWarnings("null")
    @Override
    public EntityModel<Rol> toModel(Rol rol) {
        return EntityModel.of(rol,
                linkTo(methodOn(RolControllerV2.class).find(rol.getId().longValue())).withSelfRel(),
                linkTo(methodOn(RolControllerV2.class).listar()).withRel("roles"),
                linkTo(methodOn(RolControllerV2.class).actualizar(rol.getId().longValue(), rol)).withRel("actualizar"),
                linkTo(methodOn(RolControllerV2.class).patchRol(rol.getId().longValue(), rol))
                        .withRel("actualizar-parcial"),
                linkTo(methodOn(RolControllerV2.class).delete(rol.getId().longValue())).withRel("eliminar"));
    }
}
