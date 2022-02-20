package br.com.thiaguten.microservices.localizacaoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiaguten.microservices.localizacaoservice.dto.EnderecoDTO;
import br.com.thiaguten.microservices.localizacaoservice.service.CEPService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/localizacao")
public class CEPController {

    private final CEPService cepService;

    @Autowired
    public CEPController(CEPService cepService) {
        this.cepService = cepService;
    }

    @GetMapping("/cep/{cep:\\d{8}}")
    public Mono<EnderecoDTO> buscarEnderecoPorCEP(@PathVariable("cep") String cep) {
        return cepService.obterEnderecoPeloCEP(cep);
    }

}
