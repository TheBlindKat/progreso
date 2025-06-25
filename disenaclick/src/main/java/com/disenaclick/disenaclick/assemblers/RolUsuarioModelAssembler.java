package com.disenaclick.disenaclick.assemblers;

import org.springframework.stereotype.Component;

import com.disenaclick.disenaclick.controller.v2.RolUsuarioControllerV2;
import com.disenaclick.disenaclick.model.RolUsuario;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

@Component

public class RolUsuarioModelAssembler implements RepresentationModelAssembler<RolUsuario, EntityModel<RolUsuario>> {

    @SuppressWarnings("null")
    @Override
    public EntityModel<RolUsuario> toModel(RolUsuario rolUsuario) {
        return EntityModel.of(rolUsuario,
                linkTo(methodOn(RolUsuarioControllerV2.class).find(rolUsuario.getId().longValue())).withSelfRel(),
                linkTo(methodOn(RolUsuarioControllerV2.class).listar()).withRel("rol_usuarios"),
                linkTo(methodOn(RolUsuarioControllerV2.class).actualizar(rolUsuario.getId().longValue(), rolUsuario))
                        .withRel("actualizar"),
                linkTo(methodOn(RolUsuarioControllerV2.class).patchRolUsuario(rolUsuario.getId().longValue(),
                        rolUsuario)).withRel("actualizar-parcial"),
                linkTo(methodOn(RolUsuarioControllerV2.class).delete(rolUsuario.getId().longValue()))
                        .withRel("eliminar"));
    }

}