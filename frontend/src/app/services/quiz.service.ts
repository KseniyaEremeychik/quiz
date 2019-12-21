import {Injectable} from "@angular/core";
import {Quiz} from "../models/quiz";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Question} from "../models/question";
import {PageObject} from "../models/pageObject";

@Injectable()
export class QuizService {
  public currQuizList: Quiz[] = null;
  public currQuiz: Quiz = null;
  public quizPage: PageObject = null;
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

  findQuizLike(searchParam: string, page: number, size: number): Observable<PageObject> {
    return this.http.get<PageObject>(`/api/quizLike/?searchParam=${searchParam}&page=${page}&size=${size}`);
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

  getQuizByPage(categoryId: number, page: number, size: number): Observable<PageObject> {
    return this.http.get<PageObject>(`/api/quizByPage/?categoryId=${categoryId}&page=${page}&size=${size}`);
  }

  getQuizByPageAndStatus(categoryId: number, page: number, size: number, status: string): Observable<PageObject> {
    return this.http.get<PageObject>(`/api/quizByPageAndStatus/?categoryId=${categoryId}&page=${page}&size=${size}&status=${status}`);
  }

  getAllQuiz(page: number, size: number, sortParam: string, sortFormat: number): Observable<PageObject> {
    if (sortParam) {
      return this.http.get<PageObject>(`/api/allQuiz/?page=${page}&size=${size}&sortParam=${sortParam}&sortFormat=${sortFormat}`);
    } else {
      return this.http.get<PageObject>(`/api/allQuiz/?page=${page}&size=${size}`);
    }
  }

  getAllQuizWithStatus(status: string, page: number, size: number, sortParam: string, sortFormat: number): Observable<PageObject> {
    if (sortParam) {
      return this.http.get<PageObject>(`/api/allQuizWithStatus/?page=${page}&size=${size}&status=${status}&sortParam=${sortParam}&sortFormat=${sortFormat}`);
    } else {
      return this.http.get<PageObject>(`/api/allQuizWithStatus/?page=${page}&size=${size}&status=${status}`);
    }
  }
}
