import { Component } from '@angular/core';

import { NgModel } from '@angular/forms';
import { FormControl, FormGroup } from '@angular/forms';
import { Equipe } from 'src/app/classes/Equipe';
import { Integrante } from 'src/app/classes/Integrante';
import { TeamsService } from 'src/app/service/teams-service';

@Component({
  selector: 'app-forms',
  templateUrl: './forms.component.html',
  styleUrls: ['./forms.component.scss']
})
export class FormsComponent {

  quantity: number;
  integrantes : Integrante[] = []; 
  equipe : Equipe;

  constructor(private teamsService : TeamsService){
    this.quantity = 1;
    this.equipe = new Equipe();
   }

   

  adicionarIntegrante() {
    this.integrantes = new Array(this.quantity).fill('').map(() => new Integrante());
  }

  saveTeam(form: any){
    console.log(form.value);
    this.equipe.nome = form.value.teamName;
    this.equipe.integrantes = this.integrantes;

    console.log(this.equipe);
    this.teamsService.saveTeam(this.equipe).subscribe(
      response => {
        // Lógica de manipulação da resposta do serviço, se necessário
        console.log(response);
      },
      error => {
        // Lógica de tratamento de erro, se necessário
        console.error(error);
      }
    );
  }
}
