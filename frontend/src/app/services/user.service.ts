import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "src/app/models/user";

@Injectable()
export class UserService {
  public currentUser: User = null;
  constructor(private http: HttpClient) {
  }

  saveUser(user: User): Observable<User> {
    return this.http.post<User>('/api/user', user);
  }

  getUserByEmail(email: string): Observable<User> {
    return this.http.get<User>(`/api/user/?email=${email}`);
  }

  /*getUserByEmailAndPassword(): Observable<User> {
    return this.http.post();
  }*/

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>('api/userEditing');
  }

  deleteUser(id: string): Observable<void> {
    return this.http.delete<void>('/api/user/' + id);
  }
}
