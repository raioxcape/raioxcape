package com.raioxcape.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Column(name = "pergunta", unique = true, nullable = false, length = 127)
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
