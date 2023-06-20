package com.raioxcape.backend.controller;

import com.raioxcape.backend.dto.api.ApiResponse;
import com.raioxcape.backend.dto.jogo.EnigmaUpdateDTO;
import com.raioxcape.backend.dto.jogo.JogoCreationDTO;
import com.raioxcape.backend.mapper.jogo.JogoMapper;
import com.raioxcape.backend.service.EnigmaJogoService;
import com.raioxcape.backend.service.JogoService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/jogos")
@RequiredArgsConstructor
public class JogoController {

    private final JogoService jogoService;

    private final JogoMapper jogoMapper;

    private final EnigmaJogoService enigmaJogoService;

    @PostMapping
    public ResponseEntity<ApiResponse> createJogo(@Valid @RequestBody JogoCreationDTO jogoCreationDTO) {
        HttpStatus status = HttpStatus.CREATED;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.jogoMapper.toJogoRetrievalDTO(this.jogoService.saveJogo(jogoCreationDTO))
            ),
            status
        );
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> findJogoById(@PathVariable(name = "id") int id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.jogoMapper.toJogoRetrievalDTO(this.jogoService.findJogoById(id))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findAllJogos() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.jogoService
                    .findAllJogos()
                    .stream()
                    .map(this.jogoMapper::toJogoRetrievalDTO)
                    .collect(Collectors.toList())
            ),
            status
        );
    }

    @PatchMapping(value = "/{idJogo}/enigmas/{idEnigma}")
    public ResponseEntity<ApiResponse> updateEnigmaJogoByIdEnigmaAndIdJogo(
        @PathVariable(name = "idJogo") int idJogo,
        @PathVariable(name = "idEnigma") int idEnigma,
        @Valid @RequestBody EnigmaUpdateDTO enigmaUpdateDTO
    ) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.jogoMapper
                    .toJogoRetrievalDTO(
                        this.enigmaJogoService.updateEnigmaJogoByIdEnigmaAndIdJogo(
                            idEnigma, idJogo, enigmaUpdateDTO
                        )
                        .getJogo()
                    )
            ),
            status
        );
    }
}
