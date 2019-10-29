import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "src/app/modules/register/user";

@Injectable()
export class UserService {

  constructor(private http: HttpClient) {
  }

  saveUser(user: User): Observable<User> {
    return this.http.post<User>('/api/user', user);
  }
}
