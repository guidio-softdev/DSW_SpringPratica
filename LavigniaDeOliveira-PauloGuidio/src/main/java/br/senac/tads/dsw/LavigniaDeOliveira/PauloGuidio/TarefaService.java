package br.senac.tads.dsw.LavigniaDeOliveira.PauloGuidio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    public List<TarefaDto> findAll() {
        return repository.findAll().stream()
                .sorted(Comparator.comparing(Tarefa::getDataTermino))
                .map(t -> new TarefaDto(t.getId(), t.getTitulo(), t.getResponsavel(), t.getDataTermino(), t.getDetalhamento()))
                .collect(Collectors.toList());
    }

    public TarefaDto findById(Integer id) {
        Tarefa t = repository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        return new TarefaDto(t.getId(), t.getTitulo(), t.getResponsavel(), t.getDataTermino(), t.getDetalhamento());
    }

    public TarefaDto addNew(TarefaDto dto) {
        Tarefa tarefa = new Tarefa(dto);
        tarefa = repository.save(tarefa);
        dto.setId(tarefa.getId());
        return dto;
    }

    public TarefaDto update(Integer id, TarefaDto dto) {
        Tarefa tarefa = repository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        tarefa.atualizar(dto);
        repository.save(tarefa);
        dto.setId(id);
        return dto;
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Tarefa não encontrada");
        }
        repository.deleteById(id);
    }
}