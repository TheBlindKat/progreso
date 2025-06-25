package com.disenaclick.disenaclick.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.disenaclick.disenaclick.controller.v2.PlantillaControllerV2;
import com.disenaclick.disenaclick.model.Plantilla;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PlantillaModelAssembler implements RepresentationModelAssembler<Plantilla, EntityModel<Plantilla>> {

    @SuppressWarnings("null")
    @Override
    public EntityModel<Plantilla> toModel(Plantilla plantilla) {
        return EntityModel.of(plantilla,
                linkTo(methodOn(PlantillaControllerV2.class).find(plantilla.getId().longValue())).withSelfRel(),
                linkTo(methodOn(PlantillaControllerV2.class).listar()).withRel("plantillas"),
                linkTo(methodOn(PlantillaControllerV2.class).actualizar(plantilla.getId().longValue(), plantilla))
                        .withRel("actualizar"),
                linkTo(methodOn(PlantillaControllerV2.class).patchPlantilla(plantilla.getId().longValue(), plantilla))
                        .withRel("actualizar-parcial"),
                linkTo(methodOn(PlantillaControllerV2.class).delete(plantilla.getId().longValue()))
                        .withRel("eliminar"));
    }
}
