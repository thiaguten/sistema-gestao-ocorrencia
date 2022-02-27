package br.com.thiaguten.microservices.ocorrenciaservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.thiaguten.microservices.ocorrenciaservice.model.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
    
}
