import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {InfoForStat} from "../models/infoForStat";
import {Observable} from "rxjs";
import {PageObject} from "../models/pageObject";

@Injectable()
export class StatisticsService {
  constructor(private http: HttpClient) {
  }

  addNewStatistic(newStat: InfoForStat): Observable<InfoForStat> {
    return this.http.post<InfoForStat>('/api/newStat', newStat);
  }

  getUserStatistic(userId: number, page: number, size: number, sortParam: string, sortFormat: number): Observable<PageObject> {
    if (sortParam) {
      return this.http.get<PageObject>(`/api/userStat/?userId=${userId}&page=${page}&size=${size}&sortParam=${sortParam}&sortFormat=${sortFormat}`);
    } else {
      return this.http.get<PageObject>(`/api/userStat/?userId=${userId}&page=${page}&size=${size}`);
    }
  }

  getFullStatistic(page: number, size: number, sortParam: string, sortFormat: number): Observable<PageObject> {
    if (sortParam) {
      return this.http.get<PageObject>(`/api/fullStat/?page=${page}&size=${size}&sortParam=${sortParam}&sortFormat=${sortFormat}`);
    } else {
      return this.http.get<PageObject>(`/api/fullStat/?page=${page}&size=${size}`);
    }
  }
}
