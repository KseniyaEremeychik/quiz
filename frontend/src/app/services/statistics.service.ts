import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {InfoForStat} from "../models/infoForStat";
import {Observable} from "rxjs";

@Injectable()
export class StatisticsService {
  constructor(private http: HttpClient) {
  }

  addNewStatistic(newStat: InfoForStat): Observable<InfoForStat> {
    return this.http.post<InfoForStat>('/api/newStat', newStat);
  }
}
