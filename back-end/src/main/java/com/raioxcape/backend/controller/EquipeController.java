package com.raioxcape.backend.controller;

import com.raioxcape.backend.dto.api.ApiResponse;
import com.raioxcape.backend.dto.equipe.EquipeCreationDTO;
import com.raioxcape.backend.dto.equipe.EquipeUpdateDTO;
import com.raioxcape.backend.mapper.equipe.EquipeMapper;
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

    @PostMapping
    public ResponseEntity<ApiResponse> createEquipe(@Valid @RequestBody EquipeCreationDTO equipeCreationDTO) {
        HttpStatus status = HttpStatus.CREATED;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.equipeMapper.toEquipeRetrievalDTO(this.equipeService.saveEquipe(equipeCreationDTO))
            ),
            status
        );
    }

    @PatchMapping(value = "/{nome}")
    public ResponseEntity<ApiResponse> updateEquipeByNome(
        @PathVariable(name = "nome") String nomeEquipe,
        @Valid @RequestBody EquipeUpdateDTO equipeUpdateDTO
    ) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.equipeMapper.toEquipeRetrievalDTO(this.equipeService.updateEquipeByNome(nomeEquipe, equipeUpdateDTO))
            ),
            status
        );
    }

    @GetMapping(value = "/{nome}")
    public ResponseEntity<ApiResponse> findEquipeByNome(@PathVariable(name = "nome") String nomeEquipe) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.equipeMapper.toEquipeRetrievalDTO(this.equipeService.findEquipeByNome(nomeEquipe))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findAllEquipes() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.equipeService
                    .findAllEquipes()
                    .stream()
                    .map(this.equipeMapper::toEquipeRetrievalDTO)
                    .collect(Collectors.toList())
            ),
            status
        );
    }
}
