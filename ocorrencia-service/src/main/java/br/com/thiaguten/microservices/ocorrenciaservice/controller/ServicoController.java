package br.com.thiaguten.microservices.ocorrenciaservice.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiaguten.microservices.ocorrenciaservice.exception.ServicoNotFoundException;
import br.com.thiaguten.microservices.ocorrenciaservice.model.Servico;
import br.com.thiaguten.microservices.ocorrenciaservice.service.ServicoService;
import br.com.thiaguten.microservices.ocorrenciaservice.support.dto.ServicoDTO;
import br.com.thiaguten.microservices.ocorrenciaservice.support.dto.ServicoDTOMapper;
import br.com.thiaguten.microservices.ocorrenciaservice.support.hateoas.ServicoModelAssembler;

@RestController
@RequestMapping("/api")
public class ServicoController {

    @Autowired
    private ServicoDTOMapper dtoMapper;

    private final ServicoService service;
    private final ServicoModelAssembler assembler;

    @Autowired
    public ServicoController(
            ServicoService service,
            ServicoModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping(value = "/v1/servicos", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<ServicoDTO>> listar() {
        List<Servico> servicos = service.listarServicos();
        List<EntityModel<ServicoDTO>> servicoModels = servicos.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(servicoModels, linkTo(methodOn(ServicoController.class).listar()).withSelfRel());
    }

    @PostMapping(value = "/v1/servicos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ServicoDTO>> criar(@RequestBody ServicoDTO servicoDTO) {
        Servico servico = dtoMapper.fromDto(servicoDTO);
        Servico servicoSalvo = service.salvarServico(servico);
        EntityModel<ServicoDTO> entityModel = assembler.toModel(servicoSalvo);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping(value = "/v1/servicos/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<ServicoDTO> recuperar(@PathVariable Long id) {
        return service.recuperarServico(id)
                .map(assembler::toModel)
                .orElseThrow(() -> new ServicoNotFoundException(id));
    }

    @PutMapping(value = "/v1/servicos/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ServicoDTO>> atualizar(
            @RequestBody ServicoDTO servicoDTO,
            @PathVariable Long id) {
        Servico novoServico = dtoMapper.fromDto(servicoDTO);
        Servico servicoAtualizado = service.atualizarServico(novoServico, id);
        EntityModel<ServicoDTO> entityModel = assembler.toModel(servicoAtualizado);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping(value = "/v1/servicos/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        service.deletarServico(id);
        return ResponseEntity.noContent().build();
    }

}
