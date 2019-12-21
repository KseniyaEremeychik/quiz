import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "src/app/models/user";
import {UserWithToken} from "../models/userWithToken";
import {PageObject} from "../models/pageObject";

@Injectable()
export class UserService {
  public userForRegister: User = null;
  public currentUser: User = null;

  constructor(private http: HttpClient) {
  }

  saveUser(user: User): Observable<User> {
    return this.http.post<User>('/api/user', user);
  }

  findUserByEmail(userLogin: User): Observable<UserWithToken> {
    return this.http.post<UserWithToken>('/api/userLogin', userLogin);
  }

  getAllUsers(page: number, size: number, sortParam: string, sortFormat: number): Observable<PageObject> {
    if (sortParam) {
      return this.http.get<PageObject>(`/api/userEditing/?page=${page}&size=${size}&sortParam=${sortParam}&sortFormat=${sortFormat}`);
    } else {
      return this.http.get<PageObject>(`/api/userEditing/?page=${page}&size=${size}`);
    }
  }

  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>('/api/user/' + id);
  }
}
