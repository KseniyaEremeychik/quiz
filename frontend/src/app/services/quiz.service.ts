import {Injectable} from "@angular/core";
import {Quiz} from "../models/quiz";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class QuizService {
  public currQuizList: Quiz[] = null;
  constructor(private http: HttpClient) {
  }

  findAllQuizByCategoryId(id: string): Observable<Quiz[]> {
    return this.http.get<Quiz[]>(`/api/quiz/?categoryId=${id}`);
  }
}
