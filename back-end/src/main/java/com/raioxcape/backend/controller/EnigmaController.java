package com.raioxcape.backend.controller;

import com.raioxcape.backend.dto.api.response.ApiResponse;
import com.raioxcape.backend.mapper.enigma.EnigmaMapper;
import com.raioxcape.backend.service.EnigmaService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/enigmas")
@RequiredArgsConstructor
public class EnigmaController {

    private final EnigmaService enigmaService;

    private final EnigmaMapper enigmaMapper;

    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ApiResponse> findEnigma(@PathVariable("id") int id) {
        return new ResponseEntity<>(
            new ApiResponse(
                HttpStatus.OK,
                this.enigmaMapper.toEnigmaRetrievalDTO(this.enigmaService.findById(id))
            ),
            HttpStatus.OK
        );
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ApiResponse> findEnigmas() {
        return new ResponseEntity<>(
            new ApiResponse(
                HttpStatus.OK,
                this.enigmaService
                    .findAll()
                    .stream()
                    .map(this.enigmaMapper::toEnigmaRetrievalDTO)
                    .collect(Collectors.toList())
            ),
            HttpStatus.OK
        );
    }
}
