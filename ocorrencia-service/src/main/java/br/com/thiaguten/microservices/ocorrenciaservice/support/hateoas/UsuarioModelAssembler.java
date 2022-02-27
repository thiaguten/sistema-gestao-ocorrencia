package br.com.thiaguten.microservices.ocorrenciaservice.support.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.afford;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.thiaguten.microservices.ocorrenciaservice.controller.UsuarioController;
import br.com.thiaguten.microservices.ocorrenciaservice.model.Usuario;
import br.com.thiaguten.microservices.ocorrenciaservice.support.dto.UsuarioDTO;
import br.com.thiaguten.microservices.ocorrenciaservice.support.dto.UsuarioDtoMapper;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<UsuarioDTO>> {

    @Autowired
    private UsuarioDtoMapper dtoMapper;

    @Override
    public EntityModel<UsuarioDTO> toModel(Usuario usuario) {
        Long id = usuario.getId();
        UsuarioDTO usuarioDto = dtoMapper.toDto(usuario);
        return EntityModel.of(usuarioDto,
                linkTo(methodOn(UsuarioController.class).recuperar(id)).withSelfRel()
                        .andAffordance(afford(methodOn(UsuarioController.class).atualizar(null, id)))
                        .andAffordance(afford(methodOn(UsuarioController.class).deletar(id))),
                linkTo(methodOn(UsuarioController.class).listar()).withRel("/api/v1/usuarios"));
    }

}
