package br.com.thiaguten.microservices.ocorrenciaservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thiaguten.microservices.ocorrenciaservice.model.Ocorrencia;
import br.com.thiaguten.microservices.ocorrenciaservice.repository.OcorrenciaRepository;

@Service("ocorrenciaService")
public class OcorrenciaServiceImpl implements OcorrenciaService {

    private final OcorrenciaRepository repository;

    @Autowired
    public OcorrenciaServiceImpl(OcorrenciaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Ocorrencia salvar(Ocorrencia ocorrencia) {
        return repository.save(ocorrencia);
    }

    @Override
    public Optional<Ocorrencia> recuperar(Long id) {
        return repository.findById(id);
    }

    @Override
    public Ocorrencia atualizar(Ocorrencia novaOcorrencia, Long id) {
        return recuperar(id)
                .map(ocorrencia -> {
                    ocorrencia.setDataModificacao(LocalDateTime.now());
                    ocorrencia.setDescricao(novaOcorrencia.getDescricao());
                    return salvar(ocorrencia);
                })
                .orElseGet(() -> {
                    novaOcorrencia.setId(id);
                    return salvar(novaOcorrencia);
                });
    }

    @Override
    public List<Ocorrencia> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<Ocorrencia> pesquisarPorCodigo(String codigo) {
        // TODO Auto-generated method stub
        return null;
    }

}
