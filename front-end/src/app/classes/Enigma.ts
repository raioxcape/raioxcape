import { OpcaoRespostaEnigma } from './OpcaoRespostasEnigma';
import { PortaCaminho } from './PortaCaminho';
import { NivelDificuldade } from './NivelDificuldade';

export interface Enigma {
  id: number;
  pergunta: string;
  opcoesResposta: OpcaoRespostaEnigma[];
  portaCaminho: PortaCaminho;
  nivelDificuldade: NivelDificuldade;
  tempoEstimadoSolucaoSegundos: number;
  pontos: number;
  foiSolucionado?: boolean;
  opcoesRespostaEquipe?: OpcaoRespostaEnigma[];
  tempoDecorridoSolucaoSegundos?: number;
  pontosEquipe?: number;
  criadoEm: Date;
  atualizadoEm: Date;
};
