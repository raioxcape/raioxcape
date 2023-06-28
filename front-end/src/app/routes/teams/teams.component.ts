import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import {MatDialog} from '@angular/material/dialog';
import { EquipeService } from 'src/app/service/equipe-service';
import { JogoService } from 'src/app/service/jogo-service';
import { JogoCreationDTO } from 'src/app/classes/dto/JogoCreationDTO';
import { Equipe } from 'src/app/classes/Equipe';
import { ApiResponse } from 'src/app/classes/dto/ApiResponse';
import { Jogo } from 'src/app/classes/Jogo';
import { RulesComponent } from '../rules/rules.component';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-teams',
  templateUrl: './teams.component.html',
  styleUrls: ['./teams.component.scss']
})
export class TeamsComponent {

  nomesEquipes: string[];
  isTeamSelected: boolean;
  nomeEquipe: string;
  equipeSelected: Equipe;
  jogoDTO: JogoCreationDTO;
  jogoId! : number;

  constructor(private router: Router, private equipeService: EquipeService,
    private jogoService: JogoService, private route: ActivatedRoute, public dialog : MatDialog,
    private toastr: ToastrService) {
    this.nomesEquipes = [];
    this.isTeamSelected = false;
    this.nomeEquipe = "";
    this.equipeSelected = new Equipe();
    this.jogoDTO = new JogoCreationDTO();
  }

  goToForms() {
    this.router.navigate(['forms'], { relativeTo: this.route });
  }

  goToList() {
    this.router.navigate(['list'], { relativeTo: this.route });
  }

  getTeams() {
    if (this.nomesEquipes.length === 0) {
      this.nomesEquipes = this.equipeService.getNomesEquipes();
    }
  }

  openDialog() {
    const dialogRef = this.dialog.open(RulesComponent);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  teamSelectionChanged(event: any) {
    const value = event.target.value;

    if (value && value !== 'Selecione a Equipe') {
      this.isTeamSelected = true;
      this.nomeEquipe = value;
    } else {
      this.isTeamSelected = false;
      this.nomeEquipe = "";
    }
  }

  criaJogo(nomeEquipe: string) {
    this.equipeService.getEquipe(nomeEquipe).subscribe((response: ApiResponse<Equipe>) => {
      this.equipeSelected = response.data;
      console.log(this.equipeSelected);

      this.jogoDTO.idEquipe = this.equipeSelected.id;
      console.log(this.jogoDTO);
      this.jogoService.saveJogo(this.jogoDTO).subscribe((response: ApiResponse<Jogo>) => {
        console.log(response);
        if (response.status === "CREATED") {
          this.jogoId = response.data.id;
          this.router.navigate(['/game/', this.jogoId]);
          
        } else {
          this.toastr.error("Erro ao criar jogo.", "Erro");
        }
      });
    });
  }

  ngOnInit(): void {
    this.getTeams();
  }
};
