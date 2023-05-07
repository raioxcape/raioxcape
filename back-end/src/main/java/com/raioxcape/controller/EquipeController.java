package com.raioxcape.controller;

import com.raioxcape.dto.retrieval.EquipeRetrievalDTO;
import com.raioxcape.mapper.EquipeMapper;
import com.raioxcape.service.EquipeService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/equipes")
@RequiredArgsConstructor
public class EquipeController {

    private final EquipeService equipeService;

    private final EquipeMapper equipeMapper;

    @GetMapping(value = "/{nome}")
    @ResponseStatus(value = HttpStatus.OK)
    public EquipeRetrievalDTO findEquipe(@PathVariable("nome") String nome) {
        return this.equipeMapper.toEquipeRetrievalDTO(this.equipeService.findByNome(nome));
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Set<EquipeRetrievalDTO> findEquipes() {
        return this.equipeService
            .findAll()
            .stream()
            .map(this.equipeMapper::toEquipeRetrievalDTO)
            .collect(Collectors.toSet());
    }
}
