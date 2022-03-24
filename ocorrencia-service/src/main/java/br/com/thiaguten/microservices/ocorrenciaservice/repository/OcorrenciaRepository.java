package br.com.thiaguten.microservices.ocorrenciaservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.thiaguten.microservices.ocorrenciaservice.model.Ocorrencia;

@Repository
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {

    @Query("SELECT o FROM Ocorrencia o JOIN FETCH o.usuario u JOIN FETCH o.endereco WHERE u.id = :usuarioId")
    List<Ocorrencia> findAllByUsuario(@Param("usuarioId") Long usuarioId);

}
