import { Integrante } from "./Integrante";
import { Jogo } from "./Jogo";

export class Equipe {
  id! : number;
  nome! : string;
  integrantes! : Integrante[];
  jogos? : Jogo[];
  criadaEm! : Date;
  atualizadaEm! : Date;
};
