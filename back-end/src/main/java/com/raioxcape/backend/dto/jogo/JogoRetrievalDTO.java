package com.raioxcape.backend.dto.jogo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.raioxcape.backend.dto.equipe.EquipeRetrievalDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
@JsonPropertyOrder(value = {
    "id", "equipe", "enigmas", "pontos", "numeroAcertos",
    "numeroEnigmasSolucionados", "numeroErros", "criadoEm", "atualizadoEm"
})
public class JogoRetrievalDTO {

    private final Integer id;

    private final EquipeRetrievalDTO equipe;

    private final List<EnigmaRetrievalDTO> enigmas;

    private final Integer pontos;

    private final Integer numeroEnigmasSolucionados;

    private final Integer numeroAcertos;

    private final Integer numeroErros;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private final LocalDateTime criadoEm;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private final LocalDateTime atualizadoEm;
}
