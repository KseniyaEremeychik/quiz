import {Injectable} from "@angular/core";
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpResponse
} from "@angular/common/http";
import {Router} from "@angular/router";
import {Observable} from "rxjs";
import {tap} from "rxjs/operators";
import {UserService} from "./user.service";

@Injectable()
export class ErrorHandlingInterceptor implements HttpInterceptor {
  constructor(private router: Router, private userService: UserService){}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      tap(event => {
        if (event instanceof HttpResponse) {}
      }, err => {
        if (err instanceof HttpErrorResponse) {
          if(err.status == 401) {
            this.router.navigate(['/login']);
          } else if(err.status == 404) {
            this.router.navigate(['/notFound']);
          } else if(err.status == 403) {
            this.router.navigate(['/forbidden']);
          }
        }
      })
    );
  }
}
