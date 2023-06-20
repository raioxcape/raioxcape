package com.raioxcape.backend.controller;

import com.raioxcape.backend.dto.api.ApiResponse;
import com.raioxcape.backend.mapper.enigma.EnigmaMapper;
import com.raioxcape.backend.service.EnigmaService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/enigmas")
@RequiredArgsConstructor
public class EnigmaController {

    private final EnigmaService enigmaService;

    private final EnigmaMapper enigmaMapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> findEnigmaById(@PathVariable(name = "id") int id) {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.enigmaMapper.toEnigmaRetrievalDTO(this.enigmaService.findEnigmaById(id))
            ),
            status
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> findAllEnigmas() {
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(
            new ApiResponse(
                status,
                this.enigmaService
                    .findAllEnigmas()
                    .stream()
                    .map(this.enigmaMapper::toEnigmaRetrievalDTO)
                    .collect(Collectors.toList())
            ),
            status
        );
    }
}
