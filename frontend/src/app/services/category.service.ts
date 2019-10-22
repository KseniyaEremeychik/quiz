import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Category} from "src/app/modules/home/category";

@Injectable()
// Data service
export class CategoryService { //todo create interface

  constructor(private http: HttpClient) {
  }

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>('/api/cat');
  }

}
