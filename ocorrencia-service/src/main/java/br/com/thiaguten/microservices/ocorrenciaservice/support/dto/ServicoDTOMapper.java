package br.com.thiaguten.microservices.ocorrenciaservice.support.dto;

import org.springframework.stereotype.Component;

import br.com.thiaguten.microservices.ocorrenciaservice.model.Servico;

@Component
public class ServicoDTOMapper implements DTOMapper<ServicoDTO, Servico> {

    @Override
    public ServicoDTO toDto(Servico servico) {
        ServicoDTO dto = new ServicoDTO();
        dto.setId(servico.getId());
        dto.setNome(servico.getNome());
        return dto;
    }

    @Override
    public Servico fromDto(ServicoDTO servicoDTO) {
        Servico servico = new Servico();
        servico.setId(servicoDTO.getId());
        servico.setNome(servicoDTO.getNome());
        return servico;
    }

}
