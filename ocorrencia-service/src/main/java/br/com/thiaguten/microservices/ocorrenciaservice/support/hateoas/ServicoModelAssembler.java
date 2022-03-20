package br.com.thiaguten.microservices.ocorrenciaservice.support.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.afford;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.thiaguten.microservices.ocorrenciaservice.controller.ServicoController;
import br.com.thiaguten.microservices.ocorrenciaservice.model.Servico;
import br.com.thiaguten.microservices.ocorrenciaservice.support.dto.ServicoDTO;
import br.com.thiaguten.microservices.ocorrenciaservice.support.dto.ServicoDTOMapper;

@Component
public class ServicoModelAssembler implements RepresentationModelAssembler<Servico, EntityModel<ServicoDTO>> {

    @Autowired
    private ServicoDTOMapper dtoMapper;

    @Override
    public EntityModel<ServicoDTO> toModel(Servico servico) {
        Long id = servico.getId();
        ServicoDTO servicoDto = dtoMapper.toDto(servico);
        return EntityModel.of(servicoDto,
                linkTo(methodOn(ServicoController.class).recuperar(id)).withSelfRel()
                        .andAffordance(afford(methodOn(ServicoController.class).atualizar(null, id)))
                        .andAffordance(afford(methodOn(ServicoController.class).deletar(id))),
                linkTo(methodOn(ServicoController.class).listar()).withRel("/api/v1/servicos"));
    }

}
