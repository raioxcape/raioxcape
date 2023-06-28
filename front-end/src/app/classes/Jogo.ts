import { Equipe } from './Equipe';
import { Enigma } from './Enigma';

export class Jogo {
  id!: number;
  equipe?: Equipe;
  enigmas!: Enigma[];
  pontos!: number;
  criadoEm!: Date;
  atualizadoEm!: Date;
};
