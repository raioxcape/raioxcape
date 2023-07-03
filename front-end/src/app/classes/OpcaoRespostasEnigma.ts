import { Enigma } from './Enigma';

export interface OpcaoRespostaEnigma {
  id: number;
  opcaoResposta: string;
  enigma: Enigma;
  estaCorreta: boolean;
  explicacao: string;
  foiSelecionada?: boolean;
  criadaEm: Date;
  atualizadaEm: Date;
};
