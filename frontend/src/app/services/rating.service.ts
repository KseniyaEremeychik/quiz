import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Rating} from "../models/rating";

@Injectable()
export class RatingService {
  constructor(private http: HttpClient) {
  }

  getTopTen(): Observable<Rating[]> {
    return this.http.get<Rating[]>('/api/getTop');
  }
}
