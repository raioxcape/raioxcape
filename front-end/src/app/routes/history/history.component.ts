import { Component } from '@angular/core';
import { Equipe } from 'src/app/classes/Equipe';
import { EquipeService } from 'src/app/service/equipe-service';
import { MatTableDataSource } from '@angular/material/table';
import { Jogo } from 'src/app/classes/Jogo';
import { JogoService } from 'src/app/service/jogo-service';
import { HistoryReportComponent } from './report/report.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
    styleUrls: ['./history.component.scss']
})
export class HistoryComponent {
 
  jogos: Jogo[] = [];
  displayedColumns: string[] = ['criadoEm', 'atualizadoEm', 'equipe', 'pontuacao', 'actions'];
  dataSource: any;
  isLoading: boolean = false;
  clickedRows = new Set<Jogo>();

  constructor(private jogoService: JogoService, public dialog: MatDialog) {

  }

  openDialog(row:any) {
    //console.log(row);
    this.dialog.open(HistoryReportComponent,  {
      data: row
    });
  }


  ngOnInit(): void {
    this.jogoService.getJogos().subscribe({
      next: (response: any) => {
        this.jogos = response.data;
        this.isLoading = true;
        this.dataSource = new MatTableDataSource(this.jogos);
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
           