import { Component, OnInit } from '@angular/core';

import { PortaCaminho } from 'src/app/classes/PortaCaminho';
import { JogoService } from 'src/app/service/jogo-service';
import { JogoCreationDTO } from 'src/app/classes/dto/JogoCreationDTO';

import { RulesComponent } from '../rules/rules.component';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { Jogo } from 'src/app/classes/Jogo';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {

  jogoId!: number;
  portaCaminho = PortaCaminho;
  portaCaminhoEscolhida! : string;

  constructor(public dialog: MatDialog, private route: ActivatedRoute, private jogoService: JogoService,
    private router: Router) {
      this.portaCaminhoEscolhida = "";
  }

  openDialog() {
    const dialogRef = this.dialog.open(RulesComponent);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  escolhePortaCaminho(data : PortaCaminho) {
    this.portaCaminhoEscolhida = Object.entries(PortaCaminho).find(([_, v]) => v === data)![0];

    this.router.navigate(['game/quiz', this.jogoId, this.portaCaminhoEscolhida]);
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.jogoId = params['id'];

      console.log(this.jogoId);

      this.jogoService.getJogo(this.jogoId).subscribe((data: any) => {
        console.log(data);
      });
     
    });
  }

}
