import { Component, OnInit } from '@angular/core';

import { FormBuilder, NgModel, Validators } from '@angular/forms';
import { HttpHeaders, HttpClient } from '@angular/common/http';

import {MatSnackBar, MatSnackBarModule} from '@angular/material/snack-bar';
import { ToastrService } from 'ngx-toastr';

import { Equipe } from 'src/app/classes/Equipe';
import { Integrante } from 'src/app/classes/Integrante';
import { TeamsService } from 'src/app/service/teams-service';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-forms',
  templateUrl: './forms.component.html',
  styleUrls: ['./forms.component.scss']
})
export class FormsComponent implements OnInit {

  quantity: number;
  integrantes : Integrante[] = []; 
  equipe : Equipe;
  submitted : boolean = false;
  equipeRec : string = "";

  constructor(private teamsService : TeamsService, private http : HttpClient,
    private formBuilder: FormBuilder, private toastr: ToastrService, private route: ActivatedRoute){
    this.quantity = 1;
    this.equipe = new Equipe();
    this.integrantes = [new Integrante()];

    this.formBuilder.group({
      teamName: ['', Validators.required], 
      integrantes: this.formBuilder.array([], Validators.required) 
    });
   }

  adicionarIntegrante() {
    this.integrantes = new Array(this.quantity).fill('').map(() => new Integrante());
  }

  saveTeam(form: any) {
    console.log(form);
    this.equipe.nome = form.value.teamName;
    this.equipe.integrantes = this.integrantes.map(integrante => integrante.nome);
    let message = "";

    if(form.valid) {
      this.equipe.nome = form.value.teamName;
      this.equipe.integrantes = this.integrantes.map(integrante => integrante.nome);

      this.teamsService.saveTeam(this.equipe).subscribe(
        response => {
          if(response.status === "CREATED" && response.error === null) {
            
            message = "Equipe cadastrada com sucesso!";
            this.toastr.success(message);
            form.reset();
          }
          console.log(response);
        },
        error => {
          message = "Erro ao salvar equipe.";
          this.toastr.success(message, error);
          console.error(error);
        }
      );
    } else {
      message = "Prencha todos os campos antes de enviar!";
      this.toastr.warning(message); 
    }
  }
  
  ngOnInit(){
    
  }
}
