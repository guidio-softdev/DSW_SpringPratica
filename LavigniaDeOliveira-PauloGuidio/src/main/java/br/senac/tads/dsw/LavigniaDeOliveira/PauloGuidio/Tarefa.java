package br.senac.tads.dsw.LavigniaDeOliveira.PauloGuidio;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Tarefas")
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;
    @NotBlank
    private String responsavel;
    @NotNull
    private LocalDate dataTermino;

    private String detalhamento;

    private boolean atrasada;


    public Tarefa(String titulo, String responsavel, LocalDate dataTermino,
            String detalhamento, boolean atrasada) {
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

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
