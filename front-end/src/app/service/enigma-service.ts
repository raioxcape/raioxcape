import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Enigma } from '../classes/Enigma';
import { ResourceService } from './resource-service';
import { Observable } from 'rxjs';
import { ApiResponse } from '../classes/dto/ApiResponse';

@Injectable({
  providedIn: 'root'
})
export class EnigmaService extends ResourceService<Enigma> {

  constructor(protected override http: HttpClient) {
    super(http);
  }

  override getResourceUrl(): string {
    return 'enigmas';
  }

  getEnigma(id: number): Observable<ApiResponse<Enigma>> {
    return this.getOne(id);
  }

  getEnigmas(): Observable<ApiResponse<Enigma[]>> {
    return this.getAll();
  }
};
