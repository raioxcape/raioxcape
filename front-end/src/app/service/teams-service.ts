import { Injectable } from "@angular/core";

import { HttpClient} from "@angular/common/http";
import { Observable } from "rxjs";
import { Equipe } from "../classes/Equipe";


@Injectable()
export class TeamsService {
    
    baseUrl : string = "";
    
    constructor(private http : HttpClient){
        
    }

    getTeams() {
        return this.http.get('http://localhost:8080/api/equipes');
    }

    saveTeam(equipe : Equipe): Observable<any>{
        return this.http.post('http://localhost:8080/api/equipes', equipe);
    }
}