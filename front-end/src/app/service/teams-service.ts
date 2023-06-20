import { Injectable } from "@angular/core";

import { HttpClient,  HttpHeaders} from "@angular/common/http";
import { Observable, delay, tap } from "rxjs";
import { Equipe } from "../classes/Equipe";

import { environment } from "src/environments/environment";

@Injectable({
    providedIn: "root"
})
export class TeamsService {
    
    endpoint: string = `${environment.apiBaseUrl}/equipes`;
    
    constructor(private http: HttpClient) {
        
    }

    getTeams() {
        return this.http.get<Equipe[]>(this.endpoint)
        .pipe(
            // delay(5000),
            tap()
        );
    }

    saveTeam(equipe: Equipe): Observable<any> {
        return this.http.post(this.endpoint, equipe);
    }
}
