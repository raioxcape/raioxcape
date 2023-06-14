import { Enigma } from "./Enigma";
import { OpcaoRespostaEnigmaJogo } from "./OpcaoRespostaEnigmaJogo";

export class OpcaoRespostaEnigma {
    id! : number;
    opcaoResposta! : string;
    enigma! : Enigma;
    estaCorreta! : boolean;
    explicacao! : string;
    criadaEm! : Date;
    atualizadaEm! : Date;
    jogos! : OpcaoRespostaEnigmaJogo[];
}