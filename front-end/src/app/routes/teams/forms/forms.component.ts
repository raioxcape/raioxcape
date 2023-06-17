import { Component } from '@angular/core';

import { NgModel } from '@angular/forms';
import { FormControl, FormGroup } from '@angular/forms';
import { HttpHeaders, HttpClient } from '@angular/common/http';

import {MatSnackBar, MatSnackBarModule} from '@angular/material/snack-bar';
import { ToastrService } from 'ngx-toastr/public_api';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';

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

  constructor(private teamsService : TeamsService, private http : HttpClient,
    private _snackBar: MatSnackBar, private toastr: ToastrService){
    this.quantity = 1;
    this.equipe = new Equipe();
    this.integrantes = [new Integrante()];
   }

  adicionarIntegrante() {
    this.integrantes = new Array(this.quantity).fill('').map(() => new Integrante());
    this.toastr.success('Mensagem de sucesso!', 'Título do Toast');
  }


  saveTeam(form: any) {
    this.equipe.nome = form.value.teamName;
    this.equipe.integrantes = this.integrantes.map(integrante => integrante.nome);
    let message = "";
    this.teamsService.saveTeam(this.equipe).subscribe(
      response => {
        // Lógica de manipulação da resposta do serviço, se necessário
        if(response.status === "CREATED" && response.error === null) {
          message = "Equipe cadastrada com sucesso!";
          this._snackBar.open(message, "", {
            panelClass: ['my-custom-snackbar'],
            horizontalPosition: 'right',
            verticalPosition: 'bottom',
          });
        }
        console.log(response);
      },
      error => {
        // Lógica de tratamento de erro, se necessário
        message = "Erro ao salvar equipe.";
        this._snackBar.open(message);
        console.error(error);
      }
    );
  }
  
}
