package br.senac.tads.dsw.LavigniaDeOliveira.PauloGuidio;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;
    private String responsavel;
    private LocalDate dataTermino;

    @Column(length = 500)
    private String detalhamento;

    public Tarefa() {
    }


    public Tarefa(TarefaDto dto) {
        this.titulo = dto.getTitulo();
        this.responsavel = dto.getResponsavel();
        this.dataTermino = dto.getDataTermino();
        this.detalhamento = dto.getDetalhamento();
    }


    public void atualizar(TarefaDto dto) {
        this.titulo = dto.getTitulo();
        this.responsavel = dto.getResponsavel();
        this.dataTermino = dto.getDataTermino();
        this.detalhamento = dto.getDetalhamento();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}