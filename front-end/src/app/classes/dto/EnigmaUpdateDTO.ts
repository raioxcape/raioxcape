export class EnigmaUpdateDTO {
  tempoDecorridoSolucaoSegundos!: number;
  idsOpcoesRespostaEquipe!: number[];

  constructor(tempoDecorridoSolucaoSegundos: number, idsOpcoesRespostaEquipe: number[]) {
    this.tempoDecorridoSolucaoSegundos = tempoDecorridoSolucaoSegundos;
    this.idsOpcoesRespostaEquipe = idsOpcoesRespostaEquipe
  }
};
