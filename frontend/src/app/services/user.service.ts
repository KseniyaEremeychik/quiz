import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "src/app/models/user";
import {UserWithToken} from "../models/userWithToken";

@Injectable()
export class UserService {
  public currentUser: User = null;
  constructor(private http: HttpClient) {
  }

  saveUser(user: User): Observable<User> {
    return this.http.post<User>('/api/user', user);
  }

  findUserByEmail(userLogin: User): Observable<UserWithToken> {
    return this.http.post<UserWithToken>('/api/userLogin', userLogin);
  }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>('api/userEditing');
  }

  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>('/api/user/' + id);
  }
}
