import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {RightAnswers} from "../models/rightAnswers";

@Injectable()
export class AnswerService {
  constructor(private http: HttpClient) {

  }

  getRightAnswer(userAnswersMap: Object): Observable<RightAnswers> {
    return this.http.post<RightAnswers>('/api/right', userAnswersMap);
  }
}
