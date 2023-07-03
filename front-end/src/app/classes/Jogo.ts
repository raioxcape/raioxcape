import { Equipe } from './Equipe';
import { Enigma } from './Enigma';

export class Jogo {
  id!: number;
  equipe?: Equipe;
  enigmas!: Enigma[];
  pontos!: number;
  numeroEnigmasSolucionados!: number;
  numeroAcertos!: number;
  numeroErros!: number;
  criadoEm!: Date;
  atualizadoEm!: Date;
};
