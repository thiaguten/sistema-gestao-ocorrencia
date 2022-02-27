package br.com.thiaguten.microservices.ocorrenciaservice.support.dto;

public interface DTOMapper<DTO, ENTITY> {

    DTO toDto(ENTITY entity);

    ENTITY fromDto(DTO dto);

}
