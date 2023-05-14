package com.raioxcape.backend.dto.jogo.creation;

import com.raioxcape.backend.exception.ValorInvalidoException;

import jakarta.validation.constraints.NotNull;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JogoCreationDTO {

    @NotNull(message = "O jogo precisa ter uma equipe")
    private Integer idEquipe;

    public void validate() {
        if (Objects.isNull(this.idEquipe)) {
            throw new ValorInvalidoException("O jogo precisa ter uma equipe");
        }
    }
}
