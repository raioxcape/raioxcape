import { Integrante } from "./Integrante";
import { Jogo } from "./Jogo";

export class Equipe {
    id! : number;
    nome! : string;
    criadaEm! : Date;
    atualizadaEm! : Date;
    integrantes! : Integrante[];
    jogos! : Jogo[];

}