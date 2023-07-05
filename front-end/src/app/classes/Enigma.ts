import { OpcaoRespostaEnigma } from './OpcaoRespostasEnigma';
import { PortaCaminho } from './PortaCaminho';
import { NivelDificuldade } from './NivelDificuldade';

export class Enigma {
  id!: number;
  pergunta!: string;
  opcoesResposta!: OpcaoRespostaEnigma[];
  portaCaminho!: PortaCaminho;
  nivelDificuldade!: NivelDificuldade;
  tempoEstimadoSolucaoSegundos!: number;
  pontos!: number;
  foiSolucionado?: boolean;
  opcoesRespostaEquipe?: OpcaoRespostaEnigma[];
  tempoDecorridoSolucaoSegundos?: number;
  pontosEquipe?: number;
  criadoEm!: string;
  atualizadoEm!: string;

  getNumeroOpcoesRespostaCorretas(): number {
    return this.opcoesResposta.filter((opcaoResposta: OpcaoRespostaEnigma) => opcaoResposta.estaCorreta).length;
  }
};
