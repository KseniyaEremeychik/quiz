import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {Subscription} from "rxjs";
import {InfoForStat} from "../../models/infoForStat";
import {StatisticsService} from "../../services/statistics.service";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  private subscriptions: Subscription[] = [];
  private userStatistic: InfoForStat[] = [];

  constructor(private userService: UserService,
              private statisticService: StatisticsService) { }

  ngOnInit() {
    this.getUserStatistic();
  }

  public getUserStatistic(): void {
    this.subscriptions.push(this.statisticService.getUserStatistic(this.userService.currentUser.id).subscribe((stat) => {
      this.userStatistic = stat as InfoForStat[];
    }));
  }
}
