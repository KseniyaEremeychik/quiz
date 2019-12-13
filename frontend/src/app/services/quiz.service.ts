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

  findAllQuizByCategoryId(id: number): Observable<Quiz[]> {
    return this.http.get<Quiz[]>(`/api/allQuiz/?categoryId=${id}`);
  }

  getQuizById(id: number): Observable<Question[]> {
    return this.http.get<Question[]>(`/api/quiz/?quizId=${id}`);
  }

  findQuizLike(searchParam: string): Observable<Quiz[]> {
    return this.http.get<Quiz[]>(`/api/quizLike/?searchParam=${searchParam}`);
  }

  getAllQuizByUserId(id: number): Observable<Quiz[]> {
    return this.http.get<Quiz[]>(`/api/allUsersQuiz/?userId=${id}`);
  }

  saveNewQuiz(quiz: Quiz): Observable<Quiz> {
    return this.http.post<Quiz>('/api/newQuiz', quiz);
  }

  deleteQuiz(quizId: number): Observable<void> {
    return this.http.delete<void>('/api/quizDel/' + quizId);
  }
}
