import { Component } from '@angular/core';

import { Router, ActivatedRoute  } from '@angular/router';
import { map } from 'rxjs/operators';

import { TeamsService } from 'src/app/service/teams-service';


@Component({
  selector: 'app-teams',
  templateUrl: './teams.component.html',
  styleUrls: ['./teams.component.scss']
})
export class TeamsComponent {

  listOfTeams: string[];
  isTeamSelected: boolean = false;

  constructor(private router: Router, private teamsService: TeamsService,  private route: ActivatedRoute) {
    this.listOfTeams = [];
  }  


  goToForms(){
    this.router.navigate(['forms'], { relativeTo: this.route });
  }

  goToList() {
    this.router.navigate(['list'], { relativeTo: this.route });
  }

  goToGame(){
    this.router.navigate(['./game']);
  }

  getTeams() {
    if(this.listOfTeams.length === 0) {
      let sub = this.teamsService.getTeams().subscribe((response: any) => {
        this.listOfTeams = response.data.map((equipe: { nome: any; }) => equipe.nome);        
      });
    } 
  }

  teamSelectionChanged(event: any) {
    const value = event.target.value;
    if (value && value !== 'Selecione a Equipe') {
      this.isTeamSelected = true;
    } else {
      this.isTeamSelected = false;
    }
  }

  ngOnInit() {
    this.getTeams();
  }
  
}
