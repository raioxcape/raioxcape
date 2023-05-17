package com.raioxcape.backend.controller;

import com.raioxcape.backend.dto.api.ApiResponse;
import com.raioxcape.backend.mapper.jogo.JogoMapper;
import com.raioxcape.backend.service.JogoService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/jogos")
@RequiredArgsConstructor
public class JogoController {

    private final JogoService jogoService;

    private final JogoMapper jogoMapper;

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
}
