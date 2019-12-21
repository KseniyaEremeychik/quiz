import {Injectable} from "@angular/core";
import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from "@angular/common/http";
import {Observable} from "rxjs";
import {Router} from "@angular/router";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private router: Router) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem("token");
    const authReq = req.clone({
      headers: req.headers.set('Authorization', 'Bearer' + token)
    });

    if (token) {
      return next.handle(authReq)
    } else if (req.url === "/api/catAll" || req.url === "/api/userLogin" || req.url === "/api/user" || req.url === "/api/getTop") {
      return next.handle(req);
    } else {
      this.router.navigate(['/login']);
      return next.handle(req);
    }
  }
}
