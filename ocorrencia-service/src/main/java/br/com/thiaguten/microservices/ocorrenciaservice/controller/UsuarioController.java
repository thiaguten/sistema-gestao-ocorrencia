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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiaguten.microservices.ocorrenciaservice.exception.UsuarioNotFoundException;
import br.com.thiaguten.microservices.ocorrenciaservice.model.Usuario;
import br.com.thiaguten.microservices.ocorrenciaservice.service.UsuarioService;
import br.com.thiaguten.microservices.ocorrenciaservice.support.hateoas.UsuarioModelAssembler;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioModelAssembler assembler;

    @Autowired
    public UsuarioController(UsuarioService service, UsuarioModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping(value = "/v1/usuarios", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Usuario>> listar() {
        List<Usuario> usuarios = service.listar();
        List<EntityModel<Usuario>> models = usuarios.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(models, linkTo(methodOn(UsuarioController.class).listar()).withSelfRel());
    }

    @PostMapping(value = "/v1/usuarios", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Usuario>> criar(@RequestBody Usuario usuario) {
        // Usuario usuario = new Usuario();
        // TODO popular atraves do DTO

        Usuario usuarioSalva = service.salvar(usuario);
        EntityModel<Usuario> entityModel = assembler.toModel(usuarioSalva);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping(value = "/v1/usuarios/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Usuario> recuperar(@PathVariable Long id) {
        return service.recuperar(id)
                .map(assembler::toModel)
                .orElseThrow(() -> new UsuarioNotFoundException(id));
    }

    @PutMapping(value = "/v1/usuarios/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Usuario>> atualizar(@RequestBody Usuario novoUsuario, @PathVariable Long id) {
        Usuario usuarioAtualizado = service.atualizar(novoUsuario, id);
        EntityModel<Usuario> entityModel = assembler.toModel(usuarioAtualizado);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping(value = "/v1/usuarios/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
