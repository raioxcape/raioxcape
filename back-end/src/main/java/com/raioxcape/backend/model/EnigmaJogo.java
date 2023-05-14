package com.raioxcape.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "EnigmaJogo")
@Table(
    name = "enigma_jogo",
    schema = "raioxcape",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"id_enigma", "id_jogo"})}
)
@Getter
@Setter
@ToString(doNotUseGetters = true)
public class EnigmaJogo {

    @Id
    @Column(name = "id_enigma_jogo", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_enigma", nullable = false)
    private Enigma enigma;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_jogo", nullable = false)
    private Jogo jogo;

    @Column(name = "foi_solucionado", columnDefinition = "BIT", length = 1, nullable = false)
    private Boolean foiSolucionado;

    @Column(name = "tempo_decorrido_solucao_segundos", nullable = false)
    private Integer tempoDecorridoSolucaoSegundos;

    @Column(name = "pontos", nullable = false)
    private Integer pontos;

    @Column(name = "criado_em", insertable = false, updatable = false, nullable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em", insertable = false)
    private LocalDateTime atualizadoEm;

    @OneToMany(mappedBy = "enigmaJogo", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private List<OpcaoRespostaEnigmaJogo> respostas;

    public EnigmaJogo() {
        this.respostas = new ArrayList<>();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.enigma.getPergunta(), this.jogo.getId());
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (Objects.isNull(object)) return false;

        if (this.getClass() != object.getClass()) return false;

        EnigmaJogo enigmaJogo = (EnigmaJogo) object;

        return
            Objects.equals(this.enigma.getPergunta(), enigmaJogo.enigma.getPergunta()) &&
            Objects.equals(this.jogo.getId(), enigmaJogo.jogo.getId());
    }
}
