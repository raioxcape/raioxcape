import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { EquipeService } from 'src/app/service/equipe-service';

@Component({
  selector: 'app-teams',
  templateUrl: './teams.component.html',
  styleUrls: ['./teams.component.scss']
})
export class TeamsComponent {

  nomesEquipes: string[];
  isTeamSelected: boolean;

  constructor(private router: Router, private equipeService: EquipeService, private route: ActivatedRoute) {
    this.nomesEquipes = [];
    this.isTeamSelected = false;
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

  teamSelectionChanged(event: any) {
    const value = event.target.value;

    if (value && value !== 'Selecione a Equipe') {
      this.isTeamSelected = true;
    } else {
      this.isTeamSelected = false;
    }
  }

  ngOnInit(): void {
    this.getTeams();
  }
};
