import { Injectable } from "@angular/core";

import { HttpClient,  HttpHeaders} from "@angular/common/http";
import { Observable } from "rxjs";
import { Equipe } from "../classes/Equipe";

@Injectable({
    providedIn: 'root'
  })
export class TeamsService {
    
    baseUrl : string = "";
    
    constructor(private http : HttpClient){
        
    }

    getTeams() {
        return this.http.get('api/equipes');
    }

    saveTeam(equipe : Equipe): Observable<any>{
        console.log(JSON.stringify(equipe));
        return this.http.post('api/equipes', equipe);
    }
}