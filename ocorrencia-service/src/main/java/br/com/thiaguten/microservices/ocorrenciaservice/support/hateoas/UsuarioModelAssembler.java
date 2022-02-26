package br.com.thiaguten.microservices.ocorrenciaservice.support.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.afford;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.thiaguten.microservices.ocorrenciaservice.controller.UsuarioController;
import br.com.thiaguten.microservices.ocorrenciaservice.model.Usuario;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {

    @Override
    public EntityModel<Usuario> toModel(Usuario usuario) {
        Long id = usuario.getId();
        return EntityModel.of(usuario,
                linkTo(methodOn(UsuarioController.class).recuperar(id)).withSelfRel()
                        .andAffordance(afford(methodOn(UsuarioController.class).atualizar(null, id)))
                        .andAffordance(afford(methodOn(UsuarioController.class).deletar(id))),
                linkTo(methodOn(UsuarioController.class).listar()).withRel("/api/usuarios"));
    }

}
