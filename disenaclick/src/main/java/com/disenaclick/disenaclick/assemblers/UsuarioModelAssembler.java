package com.disenaclick.disenaclick.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.disenaclick.disenaclick.controller.v2.UsuarioControllerV2;
import com.disenaclick.disenaclick.model.Usuario;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {

    @SuppressWarnings("null")
    @Override
    public EntityModel<Usuario> toModel(Usuario usuario) {
        return EntityModel.of(usuario,
                linkTo(methodOn(UsuarioControllerV2.class).find(usuario.getId().longValue())).withSelfRel(),
                linkTo(methodOn(UsuarioControllerV2.class).listar()).withRel("usuarios"),
                linkTo(methodOn(UsuarioControllerV2.class).actualizar(usuario.getId().longValue(), usuario))
                        .withRel("actualizar"),
                linkTo(methodOn(UsuarioControllerV2.class).patchUsuario(usuario.getId().longValue(), usuario))
                        .withRel("actualizar-parcial"),
                linkTo(methodOn(UsuarioControllerV2.class).delete(usuario.getId().longValue())).withRel("eliminar"));
    }
}