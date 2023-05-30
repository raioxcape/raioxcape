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
                this.jogoMapper.toJogoRetrievalDTO(this.jogoService.save(jogoCreationDTO))
            ),
            status
        );
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> findJogo(@PathVariable("id") int id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.jogoMapper.toJogoRetrievalDTO(this.jogoService.findById(id))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findJogos() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.jogoService
                    .findAll()
                    .stream()
                    .map(this.jogoMapper::toJogoRetrievalDTO)
                    .collect(Collectors.toList())
            ),
            status
        );
    }

    @PatchMapping(value = "/{idJogo}/enigmas/{idEnigma}")
    public ResponseEntity<ApiResponse> updateEnigma(
        @PathVariable("idJogo") int idJogo,
        @PathVariable("idEnigma") int idEnigma,
        @Valid @RequestBody EnigmaUpdateDTO enigmaUpdateDTO
    ) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.jogoMapper.toJogoRetrievalDTO(this.enigmaJogoService.update(idEnigma, idJogo, enigmaUpdateDTO).getJogo())
            ),
            status
        );
    }
}
