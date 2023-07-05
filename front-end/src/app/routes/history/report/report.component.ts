import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Enigma } from 'src/app/classes/Enigma';
import { Jogo } from 'src/app/classes/Jogo';
import { OpcaoRespostaEnigma } from 'src/app/classes/OpcaoRespostasEnigma';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.scss']
})
export class HistoryReportComponent implements OnInit {

  enigmasCabecaEPescoco: Enigma[];
  enigmasTorax: Enigma[];
  enigmasAbdomen: Enigma[];
  enigmasMusculoEsqueletico: Enigma[];

  constructor(public dialogRef: MatDialogRef<HistoryReportComponent>, @Inject(MAT_DIALOG_DATA) public data: Jogo) {
    this.enigmasCabecaEPescoco = this.filtrarEnigmas(data, 'CABECA_E_PESCOCO')
    this.enigmasTorax = this.filtrarEnigmas(data, 'TORAX')
    this.enigmasAbdomen = this.filtrarEnigmas(data, 'ABDOMEN')
    this.enigmasMusculoEsqueletico = this.filtrarEnigmas(data, 'MUSCULO_ESQUELETICO')
  }

  filtrarEnigmas(jogo: Jogo, portaCaminho: string): Enigma[] {
    const enigmas = jogo
      .enigmas
      .filter((enigma: Enigma) => enigma.portaCaminho == portaCaminho && enigma.foiSolucionado);

    enigmas
      .forEach(
        (enigma: Enigma) => enigma.opcoesResposta.forEach(
          (opcaoResposta: OpcaoRespostaEnigma) => {
            opcaoResposta.foiSelecionada = enigma.opcoesRespostaEquipe
              ?.map((opcaoRespostaEquipe: OpcaoRespostaEnigma) => opcaoRespostaEquipe.id).includes(opcaoResposta.id)
          }
        )
      );

    return enigmas;
  }

  determinarTipoInput(enigma: Enigma) {
    enigma = Object.assign(new Enigma(), enigma);

    return (enigma.getNumeroOpcoesRespostaCorretas() == 1) ? 'radio' : 'checkbox';
  }

  ngOnInit(): void {
    console.log(this.data);
  }
}
