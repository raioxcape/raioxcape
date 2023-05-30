package com.raioxcape.backend.dto.jogo;

import com.raioxcape.backend.exception.ValorInvalidoException;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EnigmaUpdateDTO {

    @NotNull(message = "O tempo decorrido da solução, em segundos, precisa ser fornecido")
    private Integer tempoDecorridoSolucaoSegundos;

    @NotEmpty(message = "Os ids das opções de resposta da equipe precisam ser informados")
    private List<Integer> idsOpcoesRespostaEquipe;

    public void validate() {
        if (Objects.isNull(this.tempoDecorridoSolucaoSegundos)) {
            throw new ValorInvalidoException("O tempo decorrido da solução, em segundos, precisa ser fornecido");
        }

        if (Objects.isNull(this.idsOpcoesRespostaEquipe) || this.idsOpcoesRespostaEquipe.isEmpty()) {
            throw new ValorInvalidoException("Os ids das opções de resposta da equipe precisam ser informados");
        }
    }
}
