package com.raioxcape.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Equipe")
@Table(name = "equipe", schema = "raioxcape")
@Getter
@Setter
@ToString(doNotUseGetters = true)
public class Equipe {

    @Id
    @Column(name = "id_equipe", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", unique = true, nullable = false, length = 63)
    private String nome;

    @Column(name = "criada_em", insertable = false, updatable = false, nullable = false)
    private LocalDateTime criadaEm;

    @Column(name = "atualizada_em", insertable = false)
    private LocalDateTime atualizadaEm;

    @OneToMany(mappedBy = "equipe", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    private Set<Integrante> integrantes;

    public Equipe() {
        this.integrantes = new HashSet<>();
    }

    public Equipe(String nome) {
        this();
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.nome);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (Objects.isNull(object)) return false;

        if (this.getClass() != object.getClass()) return false;

        Equipe equipe = (Equipe) object;

        return Objects.equals(this.nome, equipe.nome);
    }
}
