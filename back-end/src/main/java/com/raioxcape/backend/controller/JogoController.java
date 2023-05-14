package com.raioxcape.backend.controller;

import com.raioxcape.backend.dto.api.response.ApiResponse;
import com.raioxcape.backend.mapper.jogo.JogoMapper;
import com.raioxcape.backend.service.JogoService;

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

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ApiResponse> findJogo(@PathVariable("id") int id) {
        return new ResponseEntity<>(
            new ApiResponse(
                HttpStatus.OK,
                this.jogoMapper.toJogoRetrievalDTO(this.jogoService.findById(id))
            ),
            HttpStatus.OK
        );
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ApiResponse> findJogos() {
        return new ResponseEntity<>(
            new ApiResponse(
                HttpStatus.OK,
                this.jogoService
                    .findAll()
                    .stream()
                    .map(this.jogoMapper::toJogoRetrievalDTO)
                    .collect(Collectors.toList())
            ),
            HttpStatus.OK
        );
    }
}
