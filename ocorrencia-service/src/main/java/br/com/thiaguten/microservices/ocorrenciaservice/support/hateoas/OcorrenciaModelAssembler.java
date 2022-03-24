package br.com.thiaguten.microservices.ocorrenciaservice.support.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.thiaguten.microservices.ocorrenciaservice.controller.OcorrenciaController;
import br.com.thiaguten.microservices.ocorrenciaservice.model.Ocorrencia;
import br.com.thiaguten.microservices.ocorrenciaservice.model.SituacaoOcorrecia;
import br.com.thiaguten.microservices.ocorrenciaservice.support.dto.OcorrenciaDTO;
import br.com.thiaguten.microservices.ocorrenciaservice.support.dto.OcorrenciaDTOMapper;

@Component
public class OcorrenciaModelAssembler implements RepresentationModelAssembler<Ocorrencia, EntityModel<OcorrenciaDTO>> {

    @Autowired
    private OcorrenciaDTOMapper dtoMapper;

    @Override
    public EntityModel<OcorrenciaDTO> toModel(Ocorrencia ocorrencia) {
        Long id = ocorrencia.getId();

        OcorrenciaDTO ocorrenciaDto = dtoMapper.toDto(ocorrencia);

        EntityModel<OcorrenciaDTO> ocorrenciaModel = EntityModel.of(ocorrenciaDto,
                linkTo(methodOn(OcorrenciaController.class).recuperar(id)).withSelfRel(),
                linkTo(methodOn(OcorrenciaController.class).listar(null)).withRel("/api/v1/ocorrencias"));

        // Links condicionais baseados na situacao da ocorrencia.

        if (SituacaoOcorrecia.NOVA.equals(ocorrencia.getSituacao())
                || SituacaoOcorrecia.DEVOLVIDA.equals(ocorrencia.getSituacao())) {
            // ocorrenciaModel.add(linkTo(methodOn(OcorrenciaController.class).cancelar(id)).withRel("cancelar"));
            // ocorrenciaModel.add(linkTo(methodOn(OcorrenciaController.class).aprovar(id)).withRel("aprovar"));
        }

        if (SituacaoOcorrecia.APROVADA.equals(ocorrencia.getSituacao())) {
            // ocorrenciaModel.add(linkTo(methodOn(OcorrenciaController.class).cancelar(id)).withRel("cancelar"));
            // ocorrenciaModel.add(linkTo(methodOn(OcorrenciaController.class).finalizar(id)).withRel("aprovar"));
        }

        return ocorrenciaModel;
    }

}
