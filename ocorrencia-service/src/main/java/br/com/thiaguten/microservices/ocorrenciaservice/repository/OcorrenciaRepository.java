package br.com.thiaguten.microservices.ocorrenciaservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.thiaguten.microservices.ocorrenciaservice.model.Ocorrencia;

@Repository
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {
    
}
