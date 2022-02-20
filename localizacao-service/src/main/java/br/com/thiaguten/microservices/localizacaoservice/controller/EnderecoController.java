package br.com.thiaguten.microservices.localizacaoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiaguten.microservices.localizacaoservice.dto.EnderecoDTO;
import br.com.thiaguten.microservices.localizacaoservice.service.EnderecoService;
import br.com.thiaguten.microservices.localizacaoservice.support.hateoas.EnderecoModelAssembler;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/endereco")
public class EnderecoController {

    private final EnderecoService service;
    private final EnderecoModelAssembler assembler;

    @Autowired
    public EnderecoController(EnderecoService service, EnderecoModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping(value = "/cep/{cep:\\d{8}}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<EnderecoDTO>> buscarEnderecoPorCEP(@PathVariable("cep") String cep) {
        return service
                .obterEnderecoPeloCEP(cep)
                .map(enderecoDTO -> {
                    if (enderecoDTO.getCep() == null) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(enderecoDTO);
                    }
                    return ResponseEntity.ok().body(enderecoDTO);
                });
    }

    @GetMapping(value = "/hateoas/cep/{cep:\\d{8}}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<EnderecoDTO>> buscarEnderecoPorCEP2(@PathVariable("cep") String cep) {
        var timeout = java.time.Duration.ofSeconds(5);

        // // @formatter:off
        // return service.obterEnderecoPeloCEP(cep)
        //         .map(enderecoDTO -> {
        //             EntityModel<EnderecoDTO> enderecoModel = assembler.toModel(enderecoDTO);
        //             if (enderecoDTO.getCep() == null) {
        //                 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(enderecoModel);
        //             }
        //             return ResponseEntity.ok().body(enderecoModel);
        //         })
        //         .block(timeout);
        // // @formatter:on

        var latch = new java.util.concurrent.CountDownLatch(1);
        var list = new java.util.ArrayList<EnderecoDTO>(1);

        service.obterEnderecoPeloCEP(cep)
                .doAfterTerminate(latch::countDown)
                .subscribe(list::add);

        try {
            latch.await(timeout.toMillis(), java.util.concurrent.TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        EnderecoDTO enderecoDTO = list.get(0);
        EntityModel<EnderecoDTO> enderecoModel = assembler.toModel(enderecoDTO);

        if (enderecoDTO.getCep() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(enderecoModel);
        }

        return ResponseEntity.ok().body(enderecoModel);
    }

}
