import {Injectable} from "@angular/core";
import {Quiz} from "../models/quiz";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Question} from "../models/question";

@Injectable()
export class QuizService {
  public currQuizList: Quiz[] = null;
  public currQuiz: Quiz = null;
  constructor(private http: HttpClient) {
  }

  findAllQuizByCategoryId(id: string): Observable<Quiz[]> {
    return this.http.get<Quiz[]>(`/api/allQuiz/?categoryId=${id}`);
  }

  getQuizById(id: string): Observable<Question[]> {
    return this.http.get<Question[]>(`/api/quiz/?quizId=${id}`);
  }

  findQuizLike(searchParam: string): Observable<Quiz[]> {
    return this.http.get<Quiz[]>(`/api/quizLike/?searchParam=${searchParam}`);
  }
}
