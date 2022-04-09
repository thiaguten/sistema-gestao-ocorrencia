package br.com.thiaguten.microservices.localizacaoservice.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import br.com.thiaguten.microservices.localizacaoservice.domain.Endereco;
import br.com.thiaguten.microservices.localizacaoservice.dto.EnderecoDTO;
import br.com.thiaguten.microservices.localizacaoservice.support.util.CEPUtils;
import reactor.core.publisher.Mono;

@Primary
@Repository
public class EnderecoMongoRepository implements EnderecoRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnderecoMongoRepository.class);

    private final ReactiveEnderecoRepository reactiveEnderecoRepository;

    @Autowired
    public EnderecoMongoRepository(ReactiveEnderecoRepository reactiveEnderecoRepository) {
        this.reactiveEnderecoRepository = reactiveEnderecoRepository;
    }

    @Override
    public Mono<EnderecoDTO> obterEnderecoPeloCEP(String cep) {
        LOGGER.debug("Mongo: Pesquisando o CEP: {}", cep);
        return reactiveEnderecoRepository.findByCep(cep).map(this::toDTO);
    }

    @Override
    public Mono<EnderecoDTO> salvarEnderecoPeloCEP(EnderecoDTO enderecoDTO) {
        LOGGER.debug("Cache local: Salvando o CEP: {}", enderecoDTO.getCep());
        return reactiveEnderecoRepository.insert(fromDTO(enderecoDTO)).thenReturn(enderecoDTO);
    }

    private EnderecoDTO toDTO(Endereco endereco) {
        var cep = CEPUtils.apenasDigitos(endereco.getCep());

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setLogradouro(endereco.getLogradouro());
        enderecoDTO.setLocalidade(endereco.getLocalidade());
        enderecoDTO.setCep(cep);
        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setUf(endereco.getUf());
        return enderecoDTO;
    }

    private Endereco fromDTO(EnderecoDTO endereco) {
        return new Endereco(null,
                endereco.getLogradouro(),
                endereco.getBairro(),
                endereco.getLocalidade(),
                endereco.getUf(),
                endereco.getCep());
    }
}
