package com.raioxcape.backend.dto.equipe;

import com.raioxcape.backend.exception.ValorInvalidoException;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EquipeUpdateDTO {

    @NotBlank(message = "A equipe precisa ter um nome")
    @Size(min = 1, max = 63, message = "O nome da equipe deve ter no mínimo 1 caractere e no máximo 63 caracteres")
    private String nome;

    public void validate() {
        if (Objects.isNull(this.nome) || this.nome.isBlank()) {
            throw new ValorInvalidoException("A equipe precisa ter um nome");
        }

        if (this.nome.length() < 1 || this.nome.length() > 63) {
            throw new ValorInvalidoException("O nome da equipe deve ter no mínimo 1 caractere e no máximo 63 caracteres");
        }
    }
}
