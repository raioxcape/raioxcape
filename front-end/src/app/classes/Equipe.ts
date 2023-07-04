import { Integrante } from "./Integrante";
import { Jogo } from "./Jogo";

export class Equipe {
  id! : number;
  nome! : string;
  integrantes! : Integrante[];
  criadaEm! : string;
  atualizadaEm! : string;
};
