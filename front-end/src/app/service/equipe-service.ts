import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Equipe } from '../classes/Equipe';
import { ResourceService } from './resource-service';
import { Observable } from 'rxjs';
import { Jogo } from '../classes/Jogo';
import { ApiResponse } from '../classes/dto/ApiResponse';
import { EquipeCreationDTO } from '../classes/dto/EquipeCreationDTO';
import { EquipeUpdateDTO } from '../classes/dto/EquipeUpdateDTO';

@Injectable({
  providedIn: 'root'
})
export class EquipeService extends ResourceService<Equipe> {

  constructor(protected override http: HttpClient) {
    super(http);
  }

  override getResourceUrl(): string {
    return 'equipes';
  }

  getEquipe(nomeEquipe: string): Observable<ApiResponse<Equipe>> {
    return this.getOne(nomeEquipe);
  }

  getEquipes(): Observable<ApiResponse<Equipe[]>> {
    return this.getAll();
  }

  getJogosEquipe(id: number): Observable<ApiResponse<Jogo>> {
    return this.get(`${this.endpointUrl}/${id}/jogos`);
  }

  saveEquipe(equipe: EquipeCreationDTO): Observable<ApiResponse<Equipe>> {
    return this.save(equipe);
  }

  updateEquipe(nomeEquipe: string, payload: EquipeUpdateDTO): Observable<ApiResponse<Equipe>> {
    return this.patch(nomeEquipe, payload);
  }

  getNomesEquipes(): string[] {
    let nomesEquipes: string[] = [];

    this
      .getEquipes()
      .subscribe((response: ApiResponse<Equipe[]>) => {
        response.data.forEach((equipe: Equipe) => nomesEquipes.push(equipe.nome.trim()));
      });

    return nomesEquipes;
  }
};