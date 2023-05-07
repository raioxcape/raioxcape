DROP DATABASE IF EXISTS raioxcape;

CREATE DATABASE IF NOT EXISTS raioxcape CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

USE raioxcape;

DROP TABLE IF EXISTS resposta_enigma;

DROP TABLE IF EXISTS resposta_enigma_jogo;

DROP TABLE IF EXISTS enigma_jogo;

DROP TABLE IF EXISTS enigma;

DROP TABLE IF EXISTS jogo;

DROP TABLE IF EXISTS integrante;

DROP TABLE IF EXISTS equipe;

CREATE TABLE IF NOT EXISTS equipe (
    id_equipe     INT         NOT NULL AUTO_INCREMENT,
    nome          VARCHAR(63) NOT NULL,
    criada_em     DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizada_em DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id_equipe),
    UNIQUE      (nome)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS integrante (
    id_integrante INT         NOT NULL AUTO_INCREMENT,
    nome          VARCHAR(63) NOT NULL,
    id_equipe     INT         NOT NULL,
    criado_em     DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id_integrante),
    FOREIGN KEY (id_equipe) REFERENCES equipe (id_equipe),
    UNIQUE      (nome, id_equipe)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS jogo (
    id_jogo       INT      NOT NULL AUTO_INCREMENT,
    id_equipe     INT      NOT NULL,
    criado_em     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id_jogo),
    FOREIGN KEY (id_equipe) REFERENCES equipe (id_equipe)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS enigma (
    id_enigma                       INT                                                               NOT NULL AUTO_INCREMENT,
    pergunta                        VARCHAR(127)                                                      NOT NULL,
    porta_caminho                   ENUM('CABECA_E_PESCOCO', 'TORAX', 'ABDOMEN', 'MUSCULO_ESQUELETICO') NOT NULL,
    nivel_dificuldade               ENUM('FACIL', 'MEDIO', 'DIFICIL')                                 NOT NULL,
    tempo_estimado_solucao_segundos INT                                                               NOT NULL,
    pontos                          INT                                                               NOT NULL,
    criado_em                       DATETIME                                                          NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em                   DATETIME                                                          NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id_enigma),
    UNIQUE      (pergunta)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS resposta_enigma (
    id_resposta_enigma INT          NOT NULL AUTO_INCREMENT,
    resposta           VARCHAR(127) NOT NULL,
    id_enigma          INT          NOT NULL,
    esta_correta       BIT          NOT NULL,
    criada_em          DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizada_em      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id_resposta_enigma),
    FOREIGN KEY (id_enigma) REFERENCES enigma (id_enigma),
    UNIQUE      (resposta, id_enigma)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS enigma_jogo (
    id_enigma_jogo                   INT      NOT NULL AUTO_INCREMENT,
    id_enigma                        INT      NOT NULL,
    id_jogo                          INT      NOT NULL,
    foi_solucionado                  BIT      NOT NULL DEFAULT 0,
    tempo_decorrido_solucao_segundos INT          NULL,
    pontos                           INT          NULL,
    criado_em                        DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em                    DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id_enigma_jogo),
    FOREIGN KEY (id_enigma) REFERENCES enigma (id_enigma),
    FOREIGN KEY (id_jogo)   REFERENCES jogo (id_jogo),
    UNIQUE      (id_enigma, id_jogo)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS resposta_enigma_jogo (
    id_resposta_enigma_jogo INT      NOT NULL AUTO_INCREMENT,
    id_resposta_enigma      INT      NOT NULL,
    id_enigma_jogo          INT      NOT NULL,
    criada_em               DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizada_em           DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id_resposta_enigma_jogo),
    FOREIGN KEY (id_resposta_enigma) REFERENCES resposta_enigma (id_resposta_enigma),
    FOREIGN KEY (id_enigma_jogo)     REFERENCES enigma_jogo (id_enigma_jogo),
    UNIQUE      (id_resposta_enigma, id_enigma_jogo)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
