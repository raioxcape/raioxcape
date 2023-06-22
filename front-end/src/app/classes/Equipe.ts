import { Integrante } from "./Integrante";
import { Jogo } from "./Jogo";

export interface Equipe {
  id : number;
  nome : string;
  integrantes : Integrante[];
  jogos : Jogo[];
  criadaEm : Date;
  atualizadaEm : Date;
};
