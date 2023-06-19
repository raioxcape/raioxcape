import { Component, OnInit} from '@angular/core';

import { TeamsService } from 'src/app/service/teams-service';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatCardModule} from '@angular/material/card';
import { Equipe } from 'src/app/classes/Equipe';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {

  teams : Equipe[] = [];
  displayedColumns: string[] = ['nome', 'criadaEm', 'atualizadaEm', 'integrantes', 'actions'];  
  dataSource : any;
  isLoading: boolean = false;
  clickedRows = new Set<Equipe>();

  
  constructor(private teamsService : TeamsService, private router: Router){

  }

  editTeam(row:any){
    console.log(row);

    //this.router.navigate(['./forms', row]);
  }

  
  ngOnInit(): void {
    
    this.teamsService.getTeams().subscribe(
      (response: any) => {
        this.teams = response.data;
        this.isLoading = true;
        this.dataSource = new MatTableDataSource(this.teams);
        console.log(response.data);
      },
      (error) => {
        console.error('Ocorreu um erro ao obter os dados das equipes:', error);
      }
    );
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}