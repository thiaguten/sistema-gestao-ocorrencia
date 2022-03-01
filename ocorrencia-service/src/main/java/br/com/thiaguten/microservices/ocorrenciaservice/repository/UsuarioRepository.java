package br.com.thiaguten.microservices.ocorrenciaservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.thiaguten.microservices.ocorrenciaservice.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.detalhe WHERE u.id = :id")
    Optional<Usuario> findByIdJoinFetch(@Param("id") Long id);

}
