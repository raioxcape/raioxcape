package com.raioxcape.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "OpcaoRespostaEnigma")
@Table(
    name = "opcao_resposta_enigma",
    schema = "raioxcape",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"opcao_resposta", "id_enigma"})}
)
@Getter
@Setter
@ToString(doNotUseGetters = true)
public class OpcaoRespostaEnigma {

    @Id
    @Column(name = "id_opcao_resposta_enigma", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "opcao_resposta", nullable = false)
    private String opcaoResposta;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_enigma", nullable = false)
    private Enigma enigma;

    @Column(name = "esta_correta", columnDefinition = "BIT", length = 1, nullable = false)
    private Boolean estaCorreta;

    @Column(name = "explicacao", length = 512)
    private String explicacao;

    @Column(name = "criada_em", insertable = false, updatable = false, nullable = false)
    private LocalDateTime criadaEm;

    @Column(name = "atualizada_em", insertable = false)
    private LocalDateTime atualizadaEm;

    @OneToMany(mappedBy = "opcaoRespostaEnigma", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private List<OpcaoRespostaEnigmaJogo> jogos;

    public OpcaoRespostaEnigma() {
        this.jogos = new ArrayList<>();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.opcaoResposta, this.enigma.getPergunta());
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (Objects.isNull(object)) return false;

        if (this.getClass() != object.getClass()) return false;

        OpcaoRespostaEnigma opcaoRespostaEnigma = (OpcaoRespostaEnigma) object;

        return
            Objects.equals(this.opcaoResposta, opcaoRespostaEnigma.opcaoResposta) &&
            Objects.equals(this.enigma.getPergunta(), opcaoRespostaEnigma.enigma.getPergunta());
    }
}
