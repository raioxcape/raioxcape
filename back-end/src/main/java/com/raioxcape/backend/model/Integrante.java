package com.raioxcape.backend.model;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

import java.util.Objects;

@Entity(name = "Integrante")
@Table(
    name = "integrante",
    schema = "raioxcape",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"nome", "id_equipe"})}
)
@Getter
@Setter
@NoArgsConstructor
@ToString(doNotUseGetters = true)
public class Integrante {

    @Id
    @Column(name = "id_integrante", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 63)
    private String nome;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_equipe", nullable = false)
    private Equipe equipe;

    @Column(name = "criado_em", insertable = false, updatable = false, nullable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em", insertable = false)
    private LocalDateTime atualizadoEm;

    public Integrante(String nome, Equipe equipe) {
        this.nome = nome;
        this.equipe = equipe;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.nome, this.equipe.getNome());
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (Objects.isNull(object)) return false;

        if (this.getClass() != object.getClass()) return false;

        Integrante integrante = (Integrante) object;

        return
            Objects.equals(this.nome, integrante.nome) &&
            Objects.equals(this.equipe.getNome(), integrante.equipe.getNome());
    }
}
