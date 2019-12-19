import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs";
import {UserService} from "../services/user.service";

@Injectable()
export class AuthGuard implements CanActivate {


  constructor(private userService: UserService, private _router: Router) {
  }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    const user = this.userService.currentUser;

    if (user == null) {
      return true;
    }

    // navigate to main page
    this._router.navigate(['/']);
    return false;
  }

}
