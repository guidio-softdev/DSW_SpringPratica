package br.senac.tads.dsw.LavigniaDeOliveira.PauloGuidio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository <Tarefa, Long> {

// herda automaticamente todos os métodos CRUD
// no caso de consultas personalizadas add metodos
}