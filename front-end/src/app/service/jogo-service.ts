import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Jogo } from '../classes/Jogo';
import { ResourceService } from './resource-service';
import { Observable, catchError } from 'rxjs';
import { ApiResponse } from '../classes/dto/ApiResponse';
import { JogoCreationDTO } from '../classes/dto/JogoCreationDTO';
import { EnigmaUpdateDTO } from '../classes/dto/EnigmaUpdateDTO';

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

  saveJogo(jogo: JogoCreationDTO): Observable<ApiResponse<Jogo>> {
    return this.save(JSON.stringify(jogo));
  }

  getJogo(id: number): Observable<ApiResponse<Jogo>> {
    return this.getOne(id);
  }

  getJogos(): Observable<ApiResponse<Jogo[]>> {
    return this.getAll();
  }

  updateEnigmaJogo(idEnigma: number, idJogo: number, payload: EnigmaUpdateDTO): Observable<ApiResponse<Jogo>> {
    return this
      .http
      .patch<ApiResponse<Jogo>>(`${this.endpointUrl}/${idJogo}/enigmas/${idEnigma}`, payload)
      .pipe(catchError(this.handleError));
  }
};
