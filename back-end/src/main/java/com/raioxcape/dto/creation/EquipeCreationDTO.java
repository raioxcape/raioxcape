package com.raioxcape.dto.creation;

import com.raioxcape.exception.ValorInvalidoException;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EquipeCreationDTO {

    @NotBlank(message = "A equipe precisa ter um nome.")
    private String nome;

    @NotEmpty(message = "A equipe precisa ter pelo menos um integrante.")
    private List<String> integrantes;

    public void validate() {
        if (Objects.isNull(this.nome) || this.nome.isBlank()) {
            throw new ValorInvalidoException("A equipe precisa ter um nome.");
        }

        if (Objects.isNull(this.integrantes) || this.integrantes.isEmpty()) {
            throw new ValorInvalidoException("A equipe precisa ter pelo menos um integrante.");
        }

        for (String integrante : this.integrantes) {
            if (Objects.isNull(integrante) || integrante.isBlank()) {
                throw new ValorInvalidoException("O(s) nome(s) do(s) integrante(s) da equipe precisa(m) ser informado(s).");
            }
        }

        Set<String> integrantesUnicos = new HashSet<>(integrantes);

        if (integrantesUnicos.size() < integrantes.size()) {
            throw new ValorInvalidoException("A equipe não pode ter integrantes com o mesmo nome.");
        }
    }
}
