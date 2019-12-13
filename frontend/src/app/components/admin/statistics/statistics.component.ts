import { Component, OnInit } from '@angular/core';
import {Subscription} from "rxjs";
import {InfoForStat} from "../../../models/infoForStat";
import {StatisticsService} from "../../../services/statistics.service";

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnInit {
  private subscriptions: Subscription[] = [];
  private fullStat: InfoForStat[] = [];

  constructor(private statisticService: StatisticsService) { }

  ngOnInit() {
    this.getAllStatistic();
  }

  public getAllStatistic(): void {
    this.subscriptions.push(this.statisticService.getFullStatistic().subscribe((stat) => {
      this.fullStat = stat as InfoForStat[];
      console.log(this.fullStat);
    }));
  }
}
