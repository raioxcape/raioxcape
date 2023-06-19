import { Injectable } from "@angular/core";

import { HttpClient,  HttpHeaders} from "@angular/common/http";
import { Observable, delay, tap } from "rxjs";
import { Equipe } from "../classes/Equipe";

@Injectable({
    providedIn: 'root'
  })
export class TeamsService {
    
    baseUrl : string = "api/equipes";
    
    constructor(private http : HttpClient){
        
    }

    getTeams() {
        return this.http.get<Equipe[]>(this.baseUrl)
        .pipe(
           // delay(5000),
            tap()
        );
    }

    saveTeam(equipe : Equipe): Observable<any>{
        return this.http.post(this.baseUrl, equipe);
    }
}