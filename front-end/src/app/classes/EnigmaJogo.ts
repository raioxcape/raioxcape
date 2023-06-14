import { Enigma } from "./Enigma";
import { Jogo } from "./Jogo";
import { OpcaoRespostaEnigmaJogo } from "./OpcaoRespostaEnigmaJogo";

export class EnigmaJogo {
    id! : number;
    enigma! : Enigma;
    jogo! : Jogo;
    foiSolucionado! : boolean;
    tempoDecorridoSolucaoSegundos! : number;
    pontos! : number;
    criaoaEm! : Date;
    atualizadoEm! : Date;
    respostas! : OpcaoRespostaEnigmaJogo[];
}