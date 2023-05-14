package com.raioxcape.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Jogo")
@Table(name = "jogo", schema = "raioxcape")
@Getter
@Setter
@ToString(doNotUseGetters = true)
public class Jogo {

    @Id
    @Column(name = "id_jogo", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_equipe", nullable = false)
    private Equipe equipe;

    @Column(name = "pontos", nullable = false)
    private Integer pontos;

    @Column(name = "criado_em", insertable = false, updatable = false, nullable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em", insertable = false)
    private LocalDateTime atualizadoEm;

    @OneToMany(mappedBy = "jogo", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private List<EnigmaJogo> enigmas;

    public Jogo() {
        this.enigmas = new ArrayList<>();
    }

    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (Objects.isNull(object)) return false;

        if (this.getClass() != object.getClass()) return false;

        Jogo jogo = (Jogo) object;

        return !Objects.isNull(this.id) && Objects.equals(this.id, jogo.id);
    }
}
