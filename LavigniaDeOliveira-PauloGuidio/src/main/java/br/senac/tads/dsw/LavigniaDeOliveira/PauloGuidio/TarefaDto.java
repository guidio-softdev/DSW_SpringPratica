package br.senac.tads.dsw.LavigniaDeOliveira.PauloGuidio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class TarefaDto {

    private Long id;

    @NotBlank(message = "O título é obrigatório")
    @Size(min = 3, max = 100)
    private String titulo;

    @NotBlank(message = "O responsável é obrigatório")
    private String responsavel;

    @NotNull(message = "A data de término é obrigatória")
    private LocalDate dataTermino;

    @Size(max = 500)
    private String detalhamento;

    private boolean atrasada;

    public TarefaDto(){}
  
    public TarefaDto(Long id,  String titulo, String responsavel,LocalDate dataTermino,String detalhamento, boolean atrasada) 
          {
        this.id = id;
        this.titulo = titulo;
        this.responsavel = responsavel;
        this.dataTermino = dataTermino;
        this.detalhamento = detalhamento;
        this.atrasada = atrasada;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getTitulo() {
        return titulo;
    }


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getResponsavel() {
        return responsavel;
    }


    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }


    public LocalDate getDataTermino() {
        return dataTermino;
    }


    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }


    public String getDetalhamento() {
        return detalhamento;
    }


    public void setDetalhamento(String detalhamento) {
        this.detalhamento = detalhamento;
    }


    public boolean isAtrasada() {
        return atrasada;
    }


    public void setAtrasada(boolean atrasada) {
        this.atrasada = atrasada;
    }


    }