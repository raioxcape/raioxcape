import { Component } from '@angular/core';

import { PortaCaminho } from 'src/app/classes/PortaCaminho'; 
import { JogoService } from 'src/app/service/jogo-service';
import { JogoCreationDTO } from 'src/app/classes/dto/JogoCreationDTO';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent {

  portaCaminho = PortaCaminho;

  constructor() {
   
  }

}
