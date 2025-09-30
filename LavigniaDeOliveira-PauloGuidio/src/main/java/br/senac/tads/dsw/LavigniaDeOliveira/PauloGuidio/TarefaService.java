package br.senac.tads.dsw.LavigniaDeOliveira.PauloGuidio;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    private final Map<String, TarefaDto> mapTarefas;

    public TarefaService() {
        mapTarefas = new HashMap<>();


        TarefaDto tarefa1 = new TarefaDto(
                "Fazer almoço da semana",
                "Fulano da Silva",
                LocalDate.parse("2025-09-20"),
                "Preparar o almoço para família");
        mapTarefas.put(tarefa1.getTitulo(), tarefa1);

        TarefaDto tarefa2 = new TarefaDto(
                "Prova de DSW",
                "Ciclano de Souza",
                LocalDate.parse("2025-10-11"),
                "Preparar o resumo da prova para usar como consulta");
        mapTarefas.put(tarefa2.getTitulo(), tarefa2);
    }


    public List<TarefaDto> findAll() {
        return mapTarefas.values().stream()
                .sorted(Comparator.comparing(TarefaDto::getDataTermino))
                .collect(Collectors.toList());
    }


    public TarefaDto addNew(TarefaDto dto) {
        mapTarefas.put(dto.getTitulo(), dto);
        return dto;
    }
}