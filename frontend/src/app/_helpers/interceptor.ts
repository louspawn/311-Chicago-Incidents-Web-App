import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpErrorResponse, HttpEvent} from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

import { ApiService } from '../_services/api.service';

const TOKEN_HEADER_STRING = 'Authorization';

@Injectable()
export class Interceptor implements HttpInterceptor {

  constructor(private apiService: ApiService, private router: Router) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq = req;
    if (this.apiService.authToken.token != null) {
      authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_STRING, 'Bearer ' + this.apiService.authToken.token)});
    }
    return next.handle(authReq).pipe(
      tap((err: any) => {
          if (err instanceof HttpErrorResponse) {
              if (err.status === 401) {
                this.router.navigate(['login']);
              }
          }
      })
    );
  }

}
