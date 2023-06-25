import { Component, OnInit } from '@angular/core';
import { EquipeService } from 'src/app/service/equipe-service';
import { MatTableDataSource } from '@angular/material/table';
import { Equipe } from 'src/app/classes/Equipe';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {

  teams: Equipe[] = [];
  displayedColumns: string[] = ['nome', 'criadaEm', 'atualizadaEm', 'integrantes', 'actions'];
  dataSource: any;
  isLoading: boolean = false;
  clickedRows = new Set<Equipe>();

  constructor(private equipeService: EquipeService, private router: Router) {

  }

  editTeam(row: any) {
    console.log(row);

    //this.router.navigate(['./forms', row]);
  }

  ngOnInit(): void {
    this.equipeService.getEquipes().subscribe({
      next: (response: any) => {
        this.teams = response.data;
        this.isLoading = true;
        this.dataSource = new MatTableDataSource(this.teams);
        console.log(response.data);
      },
      error: (error) => {
        console.error('Ocorreu um erro ao tentar obter os dados das equipes:', error);
      }
    });
  }

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  
}
