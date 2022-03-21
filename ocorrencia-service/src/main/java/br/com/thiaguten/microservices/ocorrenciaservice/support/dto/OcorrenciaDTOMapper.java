package br.com.thiaguten.microservices.ocorrenciaservice.support.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.thiaguten.microservices.ocorrenciaservice.model.Endereco;
import br.com.thiaguten.microservices.ocorrenciaservice.model.Ocorrencia;
import br.com.thiaguten.microservices.ocorrenciaservice.service.ServicoService;

@Component
public class OcorrenciaDTOMapper implements DTOMapper<OcorrenciaDTO, Ocorrencia> {

    @Autowired
    private ServicoService servicoService;

    @Override
    public OcorrenciaDTO toDto(Ocorrencia ocorrencia) {
        OcorrenciaDTO dto = new OcorrenciaDTO();
        dto.setId(ocorrencia.getId());
        dto.setCodigo(ocorrencia.getCodigo());
        dto.setDescricao(ocorrencia.getDescricao());

        // TODO tlvz aqui ocorra um LazyInicializationException...
        // tlvz esse relacionamento aqui, tenha que ser sempre EAGER.
        Endereco endereco = ocorrencia.getEndereco();

        dto.setCep(endereco.getCep());
        dto.setLogradouro(endereco.getLogradouro());
        dto.setBairro(endereco.getBairro());
        dto.setLocalidade(endereco.getLocalidade());
        dto.setUf(endereco.getUf());

        dto.setDataCriacao(ocorrencia.getDataCriacao());
        dto.setDataModificacao(ocorrencia.getDataModificacao());
        dto.setSituacao(ocorrencia.getSituacao().getDescricao());
        dto.setServicoId(ocorrencia.getServico().getId());

        return dto;
    }

    @Override
    public Ocorrencia fromDto(OcorrenciaDTO ocorrenciaDto) {
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setDescricao(ocorrenciaDto.getDescricao());

        Endereco endereco = new Endereco();
        endereco.setId(ocorrencia.getId());
        endereco.setCep(ocorrenciaDto.getCep());
        endereco.setLogradouro(ocorrenciaDto.getLogradouro());
        endereco.setBairro(ocorrenciaDto.getBairro());
        endereco.setLocalidade(ocorrenciaDto.getLocalidade());
        endereco.setUf(ocorrenciaDto.getUf());

        ocorrencia.setEndereco(endereco);
        ocorrencia.setServico(servicoService.obterReferencia(ocorrenciaDto.getServicoId()));
        return ocorrencia;
    }

}
