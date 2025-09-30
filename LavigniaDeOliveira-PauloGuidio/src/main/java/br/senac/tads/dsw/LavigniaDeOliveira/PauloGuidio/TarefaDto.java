package br.senac.tads.dsw.LavigniaDeOliveira.PauloGuidio;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class TarefaDto {

    @NotBlank(message = "O título é obrigatório")
    @Size(min = 3, max = 100)
    private String titulo;

    @NotBlank(message = "O responsável é obrigatório")
    private String responsavel;

    @NotNull(message = "A data de término é obrigatória")
    @FutureOrPresent(message = "A data de término deve ser hoje ou uma data futura")
    private LocalDate dataTermino;

    @Size(max = 500)
    private String detalhamento;


    public TarefaDto(String titulo, String responsavel, LocalDate dataTermino, String detalhamento) {
        this.titulo = titulo;
        this.responsavel = responsavel;
        this.dataTermino = dataTermino;
        this.detalhamento = detalhamento;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public void setDetalhamento(String detalhamento) {
        this.detalhamento = detalhamento;
    }

    public String getDetalhamento() {
        return detalhamento;

    }
}