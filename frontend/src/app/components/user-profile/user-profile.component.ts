import {Component, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {Subscription} from "rxjs";
import {InfoForStat} from "../../models/infoForStat";
import {StatisticsService} from "../../services/statistics.service";
import {PageObject} from "../../models/pageObject";
import {PageChangedEvent} from "ngx-bootstrap";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  private subscriptions: Subscription[] = [];
  private userStatistic: InfoForStat[] = [];
  private curPage: number = 1;
  private pageSize: number = 10;
  private sortParam: string = null;
  private pageStat: PageObject;

  //1 - asc, -1 - desc
  private sortFormat: number = 1;

  constructor(private userService: UserService,
              private statisticService: StatisticsService) {
  }

  ngOnInit() {
    this.getUserStatistic();
  }

  public getUserStatistic(): void {
    this.subscriptions.push(this.statisticService.getUserStatistic(this.userService.currentUser.id, this.curPage - 1, this.pageSize, this.sortParam, this.sortFormat).subscribe((stat) => {
      this.pageStat = stat as PageObject;
      this.userStatistic = this.pageStat.content;
    }));
  }

  pageChanged(event: PageChangedEvent) {
    this.curPage = event.page;
    this.getUserStatistic();
  }

  public setSortParam(param: string): void {
    if (this.sortParam === param) {
      this.sortFormat = -this.sortFormat;
    } else {
      this.sortParam = param;
    }
    this.updateStat();
  }

  public updateStat(): void {
    this.getUserStatistic();
  }
}
