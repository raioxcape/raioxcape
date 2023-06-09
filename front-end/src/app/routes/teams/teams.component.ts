import { Component } from '@angular/core';

import { Router } from '@angular/router';

@Component({
  selector: 'app-teams',
  templateUrl: './teams.component.html',
  styleUrls: ['./teams.component.scss']
})
export class TeamsComponent {


  constructor(private router: Router) { }  


  goToForms(){
    this.router.navigate(['/teams/forms']);
  }
}
