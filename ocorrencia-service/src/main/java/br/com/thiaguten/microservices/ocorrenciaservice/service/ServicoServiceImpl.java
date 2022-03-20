package br.com.thiaguten.microservices.ocorrenciaservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thiaguten.microservices.ocorrenciaservice.model.Servico;
import br.com.thiaguten.microservices.ocorrenciaservice.repository.ServicoRepository;

@Service("servicoService")
public class ServicoServiceImpl implements ServicoService {

    private final ServicoRepository repository;

    @Autowired
    public ServicoServiceImpl(ServicoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Servico obterReferencia(Long id) {
        return repository.getById(id);
    }

    @Override
    public Servico salvarServico(Servico servico) {
        return repository.save(servico);
    }

    @Override
    public Optional<Servico> recuperarServico(Long id) {
        return repository.findById(id);
    }

    @Override
    public Servico atualizarServico(Servico novoServico, Long id) {
        return recuperarServico(id)
                .map(servico -> {
                    servico.setNome(novoServico.getNome());
                    return salvarServico(servico);
                })
                .orElseGet(() -> {
                    novoServico.setId(id);
                    return salvarServico(novoServico);
                });
    }

    @Override
    public void deletarServico(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Servico> listarServicos() {
        return repository.findAll();
    }

}
