import { Component, Inject, OnInit } from '@angular/core';

import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Enigma } from 'src/app/classes/Enigma';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.scss']
})
export class HistoryReportComponent implements OnInit {

  enigmas1 : Enigma[];
  enigmas2 : Enigma[];
  enigmas3 : Enigma[];
  enigmas4 : Enigma[];

  

  constructor(public dialogRef: MatDialogRef<HistoryReportComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      
      this.enigmas1 = data.enigmas.filter((enigma: { portaCaminho: string; foiSolucionado: boolean; })  => enigma.portaCaminho == 'CABECA_E_PESCOCO' && enigma.foiSolucionado);
      this.enigmas2 = data.enigmas.filter((enigma: { portaCaminho: string; foiSolucionado: boolean; })  => enigma.portaCaminho == 'TORAX' && enigma.foiSolucionado);
      this.enigmas3 = data.enigmas.filter((enigma: { portaCaminho: string; foiSolucionado: boolean; })  => enigma.portaCaminho == 'ABDOMEN' && enigma.foiSolucionado);
      this.enigmas4 = data.enigmas.filter((enigma: { portaCaminho: string; foiSolucionado: boolean; })  => enigma.portaCaminho == 'MUSCULO_ESQUELETICO' && enigma.foiSolucionado);
  }

  ngOnInit(): void {
    console.log(this.data);
  }

}
