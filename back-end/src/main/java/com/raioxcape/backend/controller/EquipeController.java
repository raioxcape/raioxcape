package com.raioxcape.backend.controller;

import com.raioxcape.backend.dto.api.response.ApiResponse;
import com.raioxcape.backend.dto.equipe.creation.EquipeCreationDTO;
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
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<ApiResponse> createEquipe(@Valid @RequestBody EquipeCreationDTO equipeCreationDTO) {
        return new ResponseEntity<>(
            new ApiResponse(
                HttpStatus.OK,
                this.equipeMapper.toEquipeRetrievalDTO(this.equipeService.save(equipeCreationDTO))
            ),
            HttpStatus.OK
        );
    }

    @GetMapping(value = "/{nome}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ApiResponse> findEquipe(@PathVariable("nome") String nome) {
        return new ResponseEntity<>(
            new ApiResponse(
                HttpStatus.OK,
                this.equipeMapper.toEquipeRetrievalDTO(this.equipeService.findByNome(nome))
            ),
            HttpStatus.OK
        );
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ApiResponse> findEquipes() {
        return new ResponseEntity<>(
            new ApiResponse(
                HttpStatus.OK,
                this.equipeService
                    .findAll()
                    .stream()
                    .map(this.equipeMapper::toEquipeRetrievalDTO)
                    .collect(Collectors.toList())
            ),
            HttpStatus.OK
        );
    }

    @GetMapping(value = "/{nome}/jogos")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ApiResponse> findJogosEquipe(@PathVariable("nome") String nome) {
        return new ResponseEntity<>(
            new ApiResponse(
                HttpStatus.OK,
                this.equipeService
                    .findJogos(nome)
                    .stream()
                    .map(this.jogoMapper::toJogoRetrievalDTO)
                    .collect(Collectors.toList())
            ),
            HttpStatus.OK
        );
    }
}
