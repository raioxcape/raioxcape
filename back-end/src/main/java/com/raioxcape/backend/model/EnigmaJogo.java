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
        this.foiSolucionado = false;
        this.tempoDecorridoSolucaoSegundos = 0;
        this.pontos = 0;
    }

    public EnigmaJogo(Enigma enigma, Jogo jogo) {
        this();
        this.enigma = enigma;
        this.jogo = jogo;
    }

    private int calcularNumeroAcertosEquipe(List<Integer> idsOpcoesRespostaEquipe) {
        List<Integer> idsOpcoesRespostaCorretas = this.enigma.getIdsOpcoesRespostaCorretas();

        int numeroAcertosEquipe = 0;

        for (Integer idOpcaoRespostaEquipe : idsOpcoesRespostaEquipe) {
            if (idsOpcoesRespostaCorretas.contains(idOpcaoRespostaEquipe)) {
                numeroAcertosEquipe++;
            }
        }

        return numeroAcertosEquipe;
    }

    private double calcularPeso(List<Integer> idsOpcoesRespostaEquipe) {
        int numeroOpcoesRespostaEquipe = idsOpcoesRespostaEquipe.size();
        int numeroAcertosEquipe = this.calcularNumeroAcertosEquipe(idsOpcoesRespostaEquipe);
        int numeroOpcoesRespostaCorretas = this.enigma.getIdsOpcoesRespostaCorretas().size();

        if ((numeroAcertosEquipe & numeroOpcoesRespostaCorretas & numeroOpcoesRespostaEquipe) == 1) {
            return 1.0;
        }

        return (double) numeroAcertosEquipe / numeroOpcoesRespostaCorretas / numeroOpcoesRespostaEquipe;
    }

    private int calcularPontosEquipe(List<Integer> idsOpcoesRespostaEquipe, int tempoDecorridoSolucaoSegundos) {
        double peso = this.calcularPeso(idsOpcoesRespostaEquipe);

        int tempoEstimadoSolucaoSegundos = this.enigma.getTempoEstimadoSolucaoSegundos();
        int pontosEnigma = this.enigma.getPontos();
        int pontosEquipe;

        if (tempoDecorridoSolucaoSegundos == tempoEstimadoSolucaoSegundos) {
            pontosEquipe = pontosEnigma;
        } else if (tempoDecorridoSolucaoSegundos < tempoEstimadoSolucaoSegundos) {
            double tempoSolucaoRapida = tempoEstimadoSolucaoSegundos * 0.4;

            if (tempoDecorridoSolucaoSegundos <= tempoSolucaoRapida) {
                tempoDecorridoSolucaoSegundos += tempoSolucaoRapida;
            }

            pontosEquipe = (int) Math.ceil(pontosEnigma * ((double) tempoEstimadoSolucaoSegundos / tempoDecorridoSolucaoSegundos));
        } else {
            pontosEquipe = (int) Math.floor(pontosEnigma / ((double) tempoDecorridoSolucaoSegundos / tempoEstimadoSolucaoSegundos));
        }

        return (int) Math.round(pontosEquipe * peso);
    }

    public void update(List<Integer> idsOpcoesRespostaEquipe, int tempoDecorridoSolucaoSegundos) {
        this.tempoDecorridoSolucaoSegundos = tempoDecorridoSolucaoSegundos;
        this.pontos = this.calcularPontosEquipe(idsOpcoesRespostaEquipe, tempoDecorridoSolucaoSegundos);
        this.foiSolucionado = true;
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
