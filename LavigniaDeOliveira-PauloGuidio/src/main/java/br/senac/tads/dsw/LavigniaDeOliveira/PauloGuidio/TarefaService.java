package br.senac.tads.dsw.LavigniaDeOliveira.PauloGuidio;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TarefaService {
     private final TarefaRepository tarefaRepository;
   
public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;}

//BUSCAR
    public List<TarefaDto> findAll() {
        return tarefaRepository.findAll().stream()
                .sorted(Comparator.comparing(Tarefa::getDataTermino))
                .map(this::toDto)
                .collect(Collectors.toList());
    }

//ADICIONAR
    public TarefaDto addNew(TarefaDto dto) {
        Tarefa tarefa = toEntity(dto);
        tarefa = tarefaRepository.save(tarefa);
        return toDto(tarefa);
    }

    // Método de conversão para implementar a verificação de atraso (Requisito: se a data de término tiver passado, indicar que está atrasada [3])
    private TarefaDto toDto(Tarefa tarefa) {
        TarefaDto dto = new TarefaDto(
                tarefa.getid(),
                tarefa.getitulo(),
                tarefa.getdetalhamento (),
                tarefa.getdataTermino()
        );

        if (tarefa.getDataTermino().isBefore(LocalDate.now())) {
            dto.setatrasada(true);
        }

        return dto;
    }


    // Método auxiliar para converter DTO para a Entidade (Necessário para o save no Repository)
    private Tarefa toEntity(TarefaDto dto) {
        Tarefa t = new Tarefa();
        t.settitulo(dto.gettitulo());
        t.setdescricao(dto.getdetalhamento());
        t.setdataTermino(dto.getdataTermino());
        return t;
    }
    }//fecha service