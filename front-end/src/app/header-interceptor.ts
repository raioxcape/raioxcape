import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpHeaders } from '@angular/common/http';

@Injectable()
export class HeaderInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    const modifiedReq = req.clone({ headers });
    return next.handle(modifiedReq);
  }
}