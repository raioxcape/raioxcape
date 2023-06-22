import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ApiResponse } from '../classes/dto/ApiResponse';

@Injectable({
  providedIn: 'root'
})
export abstract class ResourceService<T> {

  protected readonly endpointUrl: string = `${environment.apiBaseUrl}/${this.getResourceUrl()}`;

  constructor(protected http: HttpClient) {

  }

  abstract getResourceUrl(): string;

  get(url: string): Observable<ApiResponse<any>> {
    return this.http.get<ApiResponse<any>>(url).pipe(catchError(this.handleError));
  }

  getAll(): Observable<ApiResponse<T[]>> {
    return this.http.get<ApiResponse<T[]>>(this.endpointUrl).pipe(catchError(this.handleError));
  }

  getOne(id: string | number): Observable<ApiResponse<T>> {
    return this.http.get<ApiResponse<T>>(`${this.endpointUrl}/${id}`).pipe(catchError(this.handleError));
  }

  save(resource: any): Observable<ApiResponse<T>> {
    return this.http.post<ApiResponse<T>>(this.endpointUrl, resource).pipe(catchError(this.handleError));
  }

  delete(id: string | number): Observable<any> {
    return this.http.delete<ApiResponse<T>>(`${this.endpointUrl}/${id}`).pipe(catchError(this.handleError));
  }

  update(id: string | number, resource: Observable<ApiResponse<T>>) {
    return this.http.patch<ApiResponse<T>>(`${this.endpointUrl}/${id}`, resource).pipe(catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    return throwError(() => error);
  }
}
