import { Equipe } from "./Equipe";

export class Integrante {
    id! : number;
    nome! : string;
    equipe! : Equipe;
    criadoEm! : Date;
    atualizadoEm! : Date;
}