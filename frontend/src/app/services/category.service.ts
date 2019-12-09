import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Category} from "src/app/models/category";
import {Quiz} from "../models/quiz";

@Injectable()
export class CategoryService {
  constructor(private http: HttpClient) {
  }

  getAllCategories(): Observable<Category[]> {
    return this.http.get<Category[]>('/api/catAll');
  }

  getAllSortedCategories(sortParam: string, sortFormat: string): Observable<Category[]> {
    return this.http.get<Category[]>(`/api/catSort/?sort=${sortParam}&format=${sortFormat}`);
  }

  deleteCategory(id: number): Observable<void> {
    return this.http.delete<void>('/api/catDel/' + id);
  }

  addCategory(category: Category): Observable<Category> {
    return this.http.post<Category>('/api/catAdd', category);
  }
}
