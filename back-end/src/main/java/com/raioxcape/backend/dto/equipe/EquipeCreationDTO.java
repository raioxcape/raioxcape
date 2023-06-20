package com.raioxcape.backend.dto.equipe;

import com.raioxcape.backend.exception.ValorInvalidoException;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

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

    @NotBlank(message = "A equipe precisa ter um nome")
    @Size(min = 1, max = 63, message = "O nome da equipe deve ter no mínimo 1 caractere e no máximo 63 caracteres")
    private String nome;

    @NotEmpty(message = "A equipe precisa ter pelo menos um integrante")
    @Size(min = 1, max = 10, message = "A equipe precisa ter no mínimo 1 integrante e no máximo 10 integrantes")
    private List<String> integrantes;

    public void validate() {
        if (Objects.isNull(this.nome) || this.nome.isBlank()) {
            throw new ValorInvalidoException("A equipe precisa ter um nome");
        }

        if (this.nome.length() < 1 || this.nome.length() > 63) {
            throw new ValorInvalidoException("O nome da equipe deve ter no mínimo 1 caractere e no máximo 63 caracteres");
        }

        if (Objects.isNull(this.integrantes) || this.integrantes.isEmpty()) {
            throw new ValorInvalidoException("A equipe precisa ter pelo menos um integrante");
        }

        if (this.integrantes.size() < 1 || this.integrantes.size() > 10) {
            throw new ValorInvalidoException("A equipe precisa ter no mínimo 1 integrante e no máximo 10 integrantes");
        }

        for (String integrante : this.integrantes) {
            if (Objects.isNull(integrante) || integrante.isBlank()) {
                throw new ValorInvalidoException("O(s) nome(s) do(s) integrante(s) da equipe precisa(m) ser informado(s)");
            }
        }

        Set<String> integrantesUnicos = new HashSet<>(integrantes);

        if (integrantesUnicos.size() < integrantes.size()) {
            throw new ValorInvalidoException("A equipe não pode ter integrantes com o mesmo nome");
        }
    }
}
