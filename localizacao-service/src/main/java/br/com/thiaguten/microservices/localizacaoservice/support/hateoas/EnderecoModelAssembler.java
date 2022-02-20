package br.com.thiaguten.microservices.localizacaoservice.support.hateoas;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.thiaguten.microservices.localizacaoservice.controller.EnderecoController;
import br.com.thiaguten.microservices.localizacaoservice.dto.EnderecoDTO;
import br.com.thiaguten.microservices.localizacaoservice.support.util.CEPUtils;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EnderecoModelAssembler implements RepresentationModelAssembler<EnderecoDTO, EntityModel<EnderecoDTO>> {

    @Override
    public EntityModel<EnderecoDTO> toModel(EnderecoDTO endereco) {
        var cep = CEPUtils.apenasDigitos(endereco.getCep());
        return EntityModel.of(endereco,
                linkTo(methodOn(EnderecoController.class).buscarEnderecoPorCEP2(cep)).withSelfRel());
    }

}
