package com.raioxcape.model;

import lombok.*;

import org.bson.types.ObjectId;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "equipes")
public class Equipe {

    @Id
    private ObjectId id;

    private String nome;

    private Set<String> integrantes;

    @CreatedDate
    private LocalDateTime criadaEm;

    @LastModifiedDate
    private LocalDateTime atualizadaEm;

    public Equipe(String nome, Set<String> integrantes) {
        this.nome = nome;
        this.integrantes = integrantes;
    }
}
