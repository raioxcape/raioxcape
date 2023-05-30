package com.raioxcape.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity(name = "Enigma")
@Table(name = "enigma", schema = "raioxcape")
@Getter
@Setter
@ToString(doNotUseGetters = true)
public class Enigma {

    @Id
    @Column(name = "id_enigma", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pergunta", unique = true, nullable = false, length = 1023)
    private String pergunta;

    @Column(name = "porta_caminho", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PortaCaminho portaCaminho;

    @Column(name = "nivel_dificuldade", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private NivelDificuldade nivelDificuldade;

    @Column(name = "tempo_estimado_solucao_segundos", nullable = false)
    private Integer tempoEstimadoSolucaoSegundos;

    @Column(name = "pontos", nullable = false)
    private Integer pontos;

    @Column(name = "criado_em", insertable = false, updatable = false, nullable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em", insertable = false)
    private LocalDateTime atualizadoEm;

    @OneToMany(mappedBy = "enigma", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private List<OpcaoRespostaEnigma> opcoesResposta;

    @OneToMany(mappedBy = "enigma", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private List<EnigmaJogo> jogos;

    public Enigma() {
        this.opcoesResposta = new ArrayList<>();
        this.jogos = new ArrayList<>();
    }

    @JsonIgnore
    public List<Integer> getIdsOpcoesRespostaCorretas() {
        return this.opcoesResposta
            .stream()
            .filter(OpcaoRespostaEnigma::getEstaCorreta)
            .map(OpcaoRespostaEnigma::getId)
            .collect(Collectors.toList());
    }

    public int calcularNumeroAcertosEquipe(List<Integer> idsOpcoesRespostaEquipe) {
        List<Integer> idsOpcoesRespostaCorretas = this.getIdsOpcoesRespostaCorretas();

        int numeroAcertosEquipe = 0;

        for (Integer idOpcaoRespostaEquipe : idsOpcoesRespostaEquipe) {
            if (idsOpcoesRespostaCorretas.contains(idOpcaoRespostaEquipe)) {
                numeroAcertosEquipe++;
            }
        }

        return numeroAcertosEquipe;
    }

    private double calcularPeso(List<Integer> idsOpcoesRespostaEquipe) {
        int numeroAcertosEquipe = this.calcularNumeroAcertosEquipe(idsOpcoesRespostaEquipe);
        int numeroOpcoesRespostaCorretas = this.getIdsOpcoesRespostaCorretas().size();

        if (numeroAcertosEquipe == numeroOpcoesRespostaCorretas) {
            return 1.0;
        }

        return (double) numeroAcertosEquipe / numeroOpcoesRespostaCorretas / idsOpcoesRespostaEquipe.size();
    }

    public int calcularPontosEquipe(List<Integer> idsOpcoesRespostaEquipe, int tempoDecorridoSolucaoSegundos) {
        double peso = this.calcularPeso(idsOpcoesRespostaEquipe);

        int pontos;

        if (tempoDecorridoSolucaoSegundos == this.tempoEstimadoSolucaoSegundos) {
            pontos = this.pontos;
        } else if (tempoDecorridoSolucaoSegundos < this.tempoEstimadoSolucaoSegundos) {
            pontos = (int) Math.ceil(this.pontos * ((double) this.tempoEstimadoSolucaoSegundos / tempoDecorridoSolucaoSegundos));
        } else {
            pontos = (int) Math.floor(this.pontos / ((double) tempoDecorridoSolucaoSegundos / this.tempoEstimadoSolucaoSegundos));
        }

        return (int) Math.round(pontos * peso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.pergunta);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (Objects.isNull(object)) return false;

        if (this.getClass() != object.getClass()) return false;

        Enigma enigma = (Enigma) object;

        return Objects.equals(this.pergunta, enigma.pergunta);
    }
}
