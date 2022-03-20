package br.com.thiaguten.microservices.ocorrenciaservice.support.dto;

import java.util.Objects;

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
        dto.setDescricao(ocorrencia.getDescricao());
        dto.setServicoId(ocorrencia.getServico().getId());
        dto.setUsuarioId(ocorrencia.getUsuario().getId());
        dto.setUsuarioIdpId(ocorrencia.getUsuario().getIdpId());
        return dto;
    }

    @Override
    public Ocorrencia fromDto(OcorrenciaDTO ocorrenciaDto) {
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setId(ocorrenciaDto.getId());
        ocorrencia.setDescricao(ocorrenciaDto.getDescricao());
        ocorrencia.setServico(servicoService.obterReferencia(ocorrenciaDto.getServicoId()));
        ocorrencia.setUsuario(usuarioService.obterReferencia(ocorrenciaDto.getUsuarioId()));
        if (Objects.isNull(ocorrencia.getUsuario())) {
            ocorrencia.setUsuario(usuarioService.recuperar(ocorrenciaDto.getUsuarioIdpId()).orElse(null));
        }
        return ocorrencia;
    }

}
