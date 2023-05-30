DROP DATABASE IF EXISTS raioxcape;

CREATE DATABASE IF NOT EXISTS raioxcape CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

USE raioxcape;

DROP TRIGGER IF EXISTS marcar_enigma_como_solucionado;

DROP TRIGGER IF EXISTS atualizar_pontos;

DROP TABLE IF EXISTS opcao_resposta_enigma_jogo;

DROP TABLE IF EXISTS opcao_resposta_enigma;

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
    pontos        INT      NOT NULL DEFAULT 0,
    criado_em     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id_jogo),
    FOREIGN KEY (id_equipe) REFERENCES equipe (id_equipe)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS enigma (
    id_enigma                       INT                                                                 NOT NULL AUTO_INCREMENT,
    pergunta                        VARCHAR(1023)                                                       NOT NULL,
    porta_caminho                   ENUM('CABECA_E_PESCOCO', 'TORAX', 'ABDOMEN', 'MUSCULO_ESQUELETICO') NOT NULL,
    nivel_dificuldade               ENUM('FACIL', 'MEDIO', 'DIFICIL')                                   NOT NULL,
    tempo_estimado_solucao_segundos INT                                                                 NOT NULL,
    pontos                          INT                                                                 NOT NULL,
    criado_em                       DATETIME                                                            NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em                   DATETIME                                                            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id_enigma)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS opcao_resposta_enigma (
    id_opcao_resposta_enigma INT          NOT NULL AUTO_INCREMENT,
    opcao_resposta           VARCHAR(255) NOT NULL,
    id_enigma                INT          NOT NULL,
    esta_correta             BIT          NOT NULL,
    explicacao               VARCHAR(512)     NULL,
    criada_em                DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizada_em            DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id_opcao_resposta_enigma),
    FOREIGN KEY (id_enigma) REFERENCES enigma (id_enigma),
    UNIQUE      (opcao_resposta, id_enigma)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS enigma_jogo (
    id_enigma_jogo                   INT      NOT NULL AUTO_INCREMENT,
    id_enigma                        INT      NOT NULL,
    id_jogo                          INT      NOT NULL,
    foi_solucionado                  BIT      NOT NULL DEFAULT 0,
    tempo_decorrido_solucao_segundos INT      NOT NULL DEFAULT 0,
    pontos                           INT      NOT NULL DEFAULT 0,
    criado_em                        DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em                    DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id_enigma_jogo),
    FOREIGN KEY (id_enigma) REFERENCES enigma (id_enigma),
    FOREIGN KEY (id_jogo)   REFERENCES jogo (id_jogo),
    UNIQUE      (id_enigma, id_jogo)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS opcao_resposta_enigma_jogo (
    id_opcao_resposta_enigma_jogo INT      NOT NULL AUTO_INCREMENT,
    id_opcao_resposta_enigma      INT      NOT NULL,
    id_enigma_jogo                INT      NOT NULL,
    criada_em                     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizada_em                 DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id_opcao_resposta_enigma_jogo),
    FOREIGN KEY (id_opcao_resposta_enigma) REFERENCES opcao_resposta_enigma (id_opcao_resposta_enigma),
    FOREIGN KEY (id_enigma_jogo)           REFERENCES enigma_jogo (id_enigma_jogo),
    UNIQUE      (id_opcao_resposta_enigma, id_enigma_jogo)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

DELIMITER $$
CREATE TRIGGER marcar_enigma_como_solucionado
AFTER INSERT ON opcao_resposta_enigma_jogo
FOR EACH ROW
BEGIN
    UPDATE enigma_jogo
    SET enigma_jogo.foi_solucionado = 1
    WHERE enigma_jogo.id_enigma_jogo = NEW.id_enigma_jogo; 
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER atualizar_pontos
AFTER UPDATE ON enigma_jogo
FOR EACH ROW
BEGIN
    UPDATE jogo
    SET jogo.pontos = (
        SELECT SUM(enigma_jogo.pontos)
        FROM enigma_jogo
        WHERE jogo.id_jogo = NEW.id_jogo
    )
    WHERE jogo.id_jogo = NEW.id_jogo; 
END $$
DELIMITER ;
