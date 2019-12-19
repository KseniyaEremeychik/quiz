import {Injectable} from "@angular/core";
import {Quiz} from "../models/quiz";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Question} from "../models/question";
import {PageQuiz} from "../models/pageQuiz";

@Injectable()
export class QuizService {
  public currQuizList: Quiz[] = null;
  public currQuiz: Quiz = null;
  public quizPage: PageQuiz = null;
  public searchParam: string = null;
  public status: string = null;
  constructor(private http: HttpClient) {
  }

  findAllQuizByCategoryId(id: number): Observable<Quiz[]> {
    return this.http.get<Quiz[]>(`/api/allQuiz/?categoryId=${id}`);
  }

  getQuizById(id: number): Observable<Question[]> {
    return this.http.get<Question[]>(`/api/quiz/?quizId=${id}`);
  }

  /*findQuizLike(searchParam: string): Observable<Quiz[]> {
    return this.http.get<Quiz[]>(`/api/quizLike/?searchParam=${searchParam}`);
  }*/

  findQuizLike(searchParam: string, page: number, size: number): Observable<PageQuiz> {
    return this.http.get<PageQuiz>(`/api/quizLike/?searchParam=${searchParam}&page=${page}&size=${size}`);
  }

  getAllQuizByUserId(id: number): Observable<Quiz[]> {
    return this.http.get<Quiz[]>(`/api/allUsersQuiz/?userId=${id}`);
  }

  saveNewQuiz(quiz: Quiz): Observable<Quiz> {
    return this.http.post<Quiz>('/api/newQuiz', quiz);
  }

  editStatus(quiz: Quiz): Observable<Quiz> {
    return this.http.post<Quiz>('/api/editStatus', quiz);
  }

  deleteQuiz(quizId: number): Observable<void> {
    return this.http.delete<void>('/api/quizDel/' + quizId);
  }

  getQuizByPage(categoryId: number, page: number, size: number): Observable<PageQuiz> {
    return this.http.get<PageQuiz>(`/api/quizByPage/?categoryId=${categoryId}&page=${page}&size=${size}`);
  }

  getQuizByPageAndStatus(categoryId: number, page: number, size: number, status: string): Observable<PageQuiz> {
    return this.http.get<PageQuiz>(`/api/quizByPageAndStatus/?categoryId=${categoryId}&page=${page}&size=${size}&status=${status}`);
  }

  getAllQuiz(page: number, size: number): Observable<PageQuiz> {
    return this.http.get<PageQuiz>(`/api/allQuiz/?page=${page}&size=${size}`)
  }

  getAllQuizWithStatus(status: string, page: number, size: number): Observable<PageQuiz> {
    return this.http.get<PageQuiz>(`/api/allQuizWithStatus/?page=${page}&size=${size}&status=${status}`);
  }
}
