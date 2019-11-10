import {Injectable} from "@angular/core";
import {User} from "src/app/modules/register/user";

@Injectable({
  providedIn: 'root'
})
export class CurrentUserService {

  private static curUser: User = null;

  constructor() {
  }

  static addCurUser(user: User): any {
    this.curUser = new User();
    this.curUser.id = user.id;
    this.curUser.userName = user.userName;
    this.curUser.email = user.email;
    this.curUser.password = user.password;
    this.curUser.role = user.role;
  }

  static isCurUser(): boolean {
    if(this.curUser != null) {
      return  true;
    }
    return false;
  }
}
