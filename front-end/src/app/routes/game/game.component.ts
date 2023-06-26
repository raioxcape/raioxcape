import { Component } from '@angular/core';

import { PortaCaminho } from 'src/app/classes/PortaCaminho'; 
import { JogoService } from 'src/app/service/jogo-service';
import { JogoCreationDTO } from 'src/app/classes/dto/JogoCreationDTO';

import { RulesComponent } from '../rules/rules.component';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent {

  portaCaminho = PortaCaminho;

  constructor(public dialog: MatDialog) {
   
  }

  openDialog() {
    const dialogRef = this.dialog.open(RulesComponent);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

}
