import { Equipe } from './Equipe';
import { Enigma } from './Enigma';

export interface Jogo {
  id: number;
  equipe?: Equipe;
  enigmas: Enigma[];
  pontos: number;
  criadoEm: Date;
  atualizadoEm: Date;
};
