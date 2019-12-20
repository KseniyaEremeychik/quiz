import { Component, OnInit } from '@angular/core';
import {Subscription} from "rxjs";
import {InfoForStat} from "../../../models/infoForStat";
import {StatisticsService} from "../../../services/statistics.service";
import {PageObject} from "../../../models/pageObject";
import {PageChangedEvent} from "ngx-bootstrap";

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnInit {
  private subscriptions: Subscription[] = [];
  private fullStat: InfoForStat[] = [];
  private curPage: number = 1;
  private pageSize: number = 15;
  private sortParam: string = null;
  private pageStat: PageObject;

  //1 - asc, -1 - desc
  private sortFormat: number = 1;

  constructor(private statisticService: StatisticsService) { }

  ngOnInit() {
    this.getAllStatistic();
  }

  public getAllStatistic(): void {
    this.subscriptions.push(this.statisticService.getFullStatistic(this.curPage-1, this.pageSize, this.sortParam, this.sortFormat).subscribe((stat) => {
      this.pageStat = stat as PageObject;
      this.fullStat = this.pageStat.content;
    }));
  }

  pageChanged(event: PageChangedEvent) {
    this.curPage = event.page;
    this.getAllStatistic();
  }

  public setSortParam(param: string): void {
    if(this.sortParam === param) {
      this.sortFormat = -this.sortFormat;
    } else {
      this.sortParam = param;
    }
    this.updateStat();
  }

  public updateStat(): void {
    this.getAllStatistic();
  }
}
