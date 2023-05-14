package com.raioxcape.backend.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

import java.util.Objects;

@Entity(name = "OpcaoRespostaEnigmaJogo")
@Table(
    name = "opcao_resposta_enigma_jogo",
    schema = "raioxcape",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"id_opcao_resposta_enigma", "id_enigma_jogo"})}
)
@Getter
@Setter
@NoArgsConstructor
@ToString(doNotUseGetters = true)
public class OpcaoRespostaEnigmaJogo {

    @Id
    @Column(name = "id_opcao_resposta_enigma_jogo", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_opcao_resposta_enigma", nullable = false)
    private OpcaoRespostaEnigma opcaoRespostaEnigma;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_enigma_jogo", nullable = false)
    private EnigmaJogo enigmaJogo;

    @Column(name = "criada_em", insertable = false, updatable = false, nullable = false)
    private LocalDateTime criadaEm;

    @Column(name = "atualizada_em", insertable = false)
    private LocalDateTime atualizadaEm;

    @Override
    public int hashCode() {
        return Objects.hash(
            this.opcaoRespostaEnigma.getOpcaoResposta(),
            this.opcaoRespostaEnigma.getEnigma().getPergunta(),
            this.enigmaJogo.getJogo().getId()
        );
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;

        if (Objects.isNull(object)) return false;

        if (this.getClass() != object.getClass()) return false;

        OpcaoRespostaEnigmaJogo opcaoRespostaEnigmaJogo = (OpcaoRespostaEnigmaJogo) object;

        return
            Objects.equals(
                this.opcaoRespostaEnigma.getOpcaoResposta(),
                opcaoRespostaEnigmaJogo.opcaoRespostaEnigma.getOpcaoResposta()
            ) &&
            Objects.equals(
                this.opcaoRespostaEnigma.getEnigma().getPergunta(),
                opcaoRespostaEnigmaJogo.opcaoRespostaEnigma.getEnigma().getPergunta()
            ) &&
            Objects.equals(
                this.enigmaJogo.getJogo().getId(),
                opcaoRespostaEnigmaJogo.enigmaJogo.getJogo().getId()
            );
    }
}
