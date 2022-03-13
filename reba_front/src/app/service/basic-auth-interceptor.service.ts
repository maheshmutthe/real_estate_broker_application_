import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BasicAuthInterceptorService implements HttpInterceptor{

  constructor() { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let newHeaders = req.headers;
    if(sessionStorage.getItem("user") && sessionStorage.getItem("token")){
      newHeaders = newHeaders.append("Authorization", sessionStorage.getItem("token") + "");
    }
    const authReq = req.clone({headers: newHeaders});
    console.log(authReq.headers);
    return next.handle(authReq);
  }
}
