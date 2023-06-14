import { EnigmaJogo } from "./EnigmaJogo";
import { NivelDificuldade } from "./NivelDificuldade";
import { OpcaoRespostaEnigma } from "./OpcaoRespostasEnigma";
import { PortaCaminho } from "./PortaCaminho";

export class Enigma {
    id! : number;
    pergunta! : string;
    portaCaminho! : PortaCaminho;
    nivelDificuldade! : NivelDificuldade;
    tempoEstimadoSolucaoSegundos! : number;
    pontos! : number;
    criaoaEm! : Date;
    atualizadoEm! : Date;
    opcoesResposta! : OpcaoRespostaEnigma;
    jogo! : EnigmaJogo[];
}