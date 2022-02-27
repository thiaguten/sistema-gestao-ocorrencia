package br.com.thiaguten.microservices.ocorrenciaservice.support.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.thiaguten.microservices.ocorrenciaservice.model.Ocorrencia;
import br.com.thiaguten.microservices.ocorrenciaservice.service.ServicoService;
import br.com.thiaguten.microservices.ocorrenciaservice.service.UsuarioService;

@Component
public class OcorrenciaDTOMapper implements DTOMapper<OcorrenciaDTO, Ocorrencia> {

    @Autowired
    private ServicoService servicoService;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public OcorrenciaDTO toDto(Ocorrencia ocorrencia) {
        OcorrenciaDTO dto = new OcorrenciaDTO();
        dto.setId(ocorrencia.getId());
        dto.setObservacao(ocorrencia.getObservacao());
        dto.setServicoId(ocorrencia.getServico().getId());
        dto.setUsuarioId(ocorrencia.getUsuario().getId());
        return dto;
    }

    @Override
    public Ocorrencia fromDto(OcorrenciaDTO ocorrenciaDto) {
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setId(ocorrenciaDto.getId());
        ocorrencia.setObservacao(ocorrenciaDto.getObservacao());
        ocorrencia.setServico(servicoService.obterReferencia(ocorrenciaDto.getServicoId()));
        ocorrencia.setUsuario(usuarioService.obterReferencia(ocorrenciaDto.getUsuarioId()));
        return ocorrencia;
    }

}
