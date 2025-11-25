package br.senac.tads.dsw.LavigniaDeOliveira.PauloGuidio;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/tarefas")
@CrossOrigin(origins = "*")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public List<TarefaDto> listarTarefas() {
        return tarefaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDto> buscarTarefa(@PathVariable Integer id) {
        return ResponseEntity.ok(tarefaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TarefaDto> criarTarefa(@Valid @RequestBody TarefaDto tarefaDto) {
        TarefaDto tarefaCriada = tarefaService.addNew(tarefaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaCriada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaDto> atualizarTarefa(@PathVariable Integer id, @Valid @RequestBody TarefaDto tarefaDto) {
        TarefaDto tarefaAtualizada = tarefaService.update(id, tarefaDto);
        return ResponseEntity.ok(tarefaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTarefa(@PathVariable Integer id) {
        tarefaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}