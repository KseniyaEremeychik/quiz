import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs";
import {UserService} from "../services/user.service";

@Injectable()
export class RoleGuard implements CanActivate {


  constructor(private userService: UserService, private _router: Router) {
  }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    const userRole = this.userService.currentUser.role;

    if (userRole === next.data.role) {
      return true;
    }

    // navigate to not found page
    this._router.navigate(['/forbidden']);
    return false;
  }

}
