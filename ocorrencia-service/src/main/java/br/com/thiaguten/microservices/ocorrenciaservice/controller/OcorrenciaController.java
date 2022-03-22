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
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiaguten.microservices.ocorrenciaservice.exception.OcorrenciaNotFoundException;
import br.com.thiaguten.microservices.ocorrenciaservice.exception.UsuarioNotFoundException;
import br.com.thiaguten.microservices.ocorrenciaservice.model.Ocorrencia;
import br.com.thiaguten.microservices.ocorrenciaservice.model.SituacaoOcorrecia;
import br.com.thiaguten.microservices.ocorrenciaservice.service.OcorrenciaService;
import br.com.thiaguten.microservices.ocorrenciaservice.service.UsuarioService;
import br.com.thiaguten.microservices.ocorrenciaservice.support.dto.OcorrenciaDTO;
import br.com.thiaguten.microservices.ocorrenciaservice.support.dto.OcorrenciaDTOMapper;
import br.com.thiaguten.microservices.ocorrenciaservice.support.hateoas.OcorrenciaModelAssembler;

//@CrossOrigin
@RestController
@RequestMapping("/api")
public class OcorrenciaController {

    @Autowired
    private UsuarioService usuarioService;

    private final OcorrenciaService service;
    private final OcorrenciaDTOMapper dtoMapper;
    private final OcorrenciaModelAssembler assembler;

    @Autowired
    public OcorrenciaController(
            OcorrenciaService service,
            OcorrenciaDTOMapper dtoMapper,
            OcorrenciaModelAssembler assembler) {
        this.service = service;
        this.dtoMapper = dtoMapper;
        this.assembler = assembler;
    }

    @GetMapping(value = "/v1/ocorrencias", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<OcorrenciaDTO>> listar() {
        List<Ocorrencia> ocorrencias = service.listar();
        List<EntityModel<OcorrenciaDTO>> models = ocorrencias.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(models, linkTo(methodOn(OcorrenciaController.class).listar()).withSelfRel());
    }

    @PostMapping(value = "/v1/ocorrencias", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<OcorrenciaDTO>> criar(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody OcorrenciaDTO ocorrenciaDto) {

        // Obter subject a partir do token de autenticacao;
        final var oauth2SubjectClaim = jwt.getSubject();
        final var usuario = usuarioService.recuperar(oauth2SubjectClaim)
                .orElseThrow(() -> new UsuarioNotFoundException(oauth2SubjectClaim));

        Ocorrencia ocorrencia = dtoMapper.fromDto(ocorrenciaDto);
        ocorrencia.setUsuario(usuario);

        Ocorrencia ocorrenciaSalva = service.salvar(ocorrencia);
        EntityModel<OcorrenciaDTO> entityModel = assembler.toModel(ocorrenciaSalva);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping(value = "/v1/ocorrencias/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<OcorrenciaDTO> recuperar(@PathVariable Long id) {
        return service.recuperar(id)
                .map(assembler::toModel)
                .orElseThrow(() -> new OcorrenciaNotFoundException(id));
    }

    @PutMapping(value = "/v1/ocorrencias/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> atualizar(@RequestBody OcorrenciaDTO novaOcorrenciaDto, @PathVariable Long id) {

        Ocorrencia ocorrencia = service.recuperar(id)
                .orElseThrow(() -> new OcorrenciaNotFoundException(id));

        if (SituacaoOcorrecia.NOVA.equals(ocorrencia.getSituacao())
                || SituacaoOcorrecia.DEVOLVIDA.equals(ocorrencia.getSituacao())) {
            ocorrencia.setDescricao(novaOcorrenciaDto.getDescricao());
            EntityModel<OcorrenciaDTO> entityModel = assembler.toModel(ocorrencia);
            return ResponseEntity
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
        }

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Operação inválida")
                        .withDetail("Não se pode atualizar uma ocorrência que esteja na situação "
                                + ocorrencia.getSituacao()));
    }
}
