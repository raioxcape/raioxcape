import { EnigmaJogo } from "./EnigmaJogo";
import { OpcaoRespostaEnigma } from "./OpcaoRespostasEnigma";

export class OpcaoRespostaEnigmaJogo {
    id! : number;
    opcaoRespostaEnigma! : OpcaoRespostaEnigma;
    enigmaJogo! : EnigmaJogo;
    criadaEm! : Date;
    atualizadaEm! : Date;
}