import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Category} from "src/app/modules/category/category";

@Injectable()
export class CategoryService {
  constructor(private http: HttpClient) {
  }

  getAllCategories(): Observable<Category[]> {
    return this.http.get<Category[]>('/api/cat');
  }

  deleteCategory(id: string): Observable<void> {
    return this.http.delete<void>('/api/cat/' + id);
  }

  addCategory(category: Category): Observable<Category> {
    return this.http.post<Category>('/api/cat', category);
  }
}
