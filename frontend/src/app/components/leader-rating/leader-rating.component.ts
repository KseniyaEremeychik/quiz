import { Component, OnInit } from '@angular/core';
import {RatingService} from "../../services/rating.service";
import {Subscription} from "rxjs";
import {Rating} from "../../models/rating";

@Component({
  selector: 'app-leader-rating',
  templateUrl: './leader-rating.component.html',
  styleUrls: ['./leader-rating.component.css']
})
export class LeaderRatingComponent implements OnInit {
  private topTen: Rating[] = [];
  private subscriptions: Subscription[] = [];

  constructor(private ratingService: RatingService) { }

  ngOnInit() {
    this.getTopTen();
  }

  public getTopTen(): void {
    this.subscriptions.push(this.ratingService.getTopTen().subscribe((rating) => {
      this.topTen = rating as Rating[];
    }));
  }
}
