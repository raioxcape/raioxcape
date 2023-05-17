package com.raioxcape.backend.controller;

import com.raioxcape.backend.dto.api.ApiResponse;
import com.raioxcape.backend.dto.equipe.EquipeCreationDTO;
import com.raioxcape.backend.mapper.equipe.EquipeMapper;
import com.raioxcape.backend.mapper.equipe.JogoMapper;
import com.raioxcape.backend.service.EquipeService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/equipes")
@RequiredArgsConstructor
public class EquipeController {

    private final EquipeService equipeService;

    private final EquipeMapper equipeMapper;

    private final JogoMapper jogoMapper;

    @PostMapping
    public ResponseEntity<ApiResponse> createEquipe(@Valid @RequestBody EquipeCreationDTO equipeCreationDTO) {
        HttpStatus status = HttpStatus.CREATED;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.equipeMapper.toEquipeRetrievalDTO(this.equipeService.save(equipeCreationDTO))
            ),
            status
        );
    }

    @GetMapping(value = "/{nome}")
    public ResponseEntity<ApiResponse> findEquipe(@PathVariable("nome") String nome) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.equipeMapper.toEquipeRetrievalDTO(this.equipeService.findByNome(nome))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findEquipes() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.equipeService
                    .findAll()
                    .stream()
                    .map(this.equipeMapper::toEquipeRetrievalDTO)
                    .collect(Collectors.toList())
            ),
            status
        );
    }

    @GetMapping(value = "/{nome}/jogos")
    public ResponseEntity<ApiResponse> findJogosEquipe(@PathVariable("nome") String nome) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.equipeService
                    .findJogos(nome)
                    .stream()
                    .map(this.jogoMapper::toJogoRetrievalDTO)
                    .collect(Collectors.toList())
            ),
            status
        );
    }
}
