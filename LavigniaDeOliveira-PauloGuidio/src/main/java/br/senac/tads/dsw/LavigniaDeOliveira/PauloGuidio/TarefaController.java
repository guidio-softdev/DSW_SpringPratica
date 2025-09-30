package br.senac.tads.dsw.LavigniaDeOliveira.PauloGuidio;



import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@CrossOrigin(origins = "*")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;


    @GetMapping
    public List<TarefaDto> listarTarefas() {
        return tarefaService.findAll();
    }


    @PostMapping
    public ResponseEntity<TarefaDto> criarTarefa(@Valid @RequestBody TarefaDto tarefaDto) {
        TarefaDto tarefaCriada = tarefaService.addNew(tarefaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaCriada);
    }
}