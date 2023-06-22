import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Jogo } from '../classes/Jogo';
import { ResourceService } from './resource-service';
import { Observable } from 'rxjs';
import { ApiResponse } from '../classes/dto/ApiResponse';

@Injectable({
  providedIn: 'root'
})
export class JogoService extends ResourceService<Jogo> {

  constructor(protected override http: HttpClient) {
    super(http);
  }

  override getResourceUrl(): string {
    return 'jogos';
  }

  getJogo(id: number): Observable<ApiResponse<Jogo>> {
    return this.getOne(id);
  }

  getJogos(): Observable<ApiResponse<Jogo[]>> {
    return this.getAll();
  }
};
