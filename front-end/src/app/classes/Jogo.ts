import { EnigmaJogo } from "./EnigmaJogo";
import { Equipe } from "./Equipe";

export class Jogo {
    id! : number;
    Equipe! : Equipe;
    pontos! : number;
    criaoaEm! : Date;
    atualizadoEm! : Date;
    enigmas! : EnigmaJogo[];
}