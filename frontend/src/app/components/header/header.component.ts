import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {BsDropdownConfig} from "ngx-bootstrap";
import {QuizService} from "../../services/quiz.service";
import {Subscription} from "rxjs";
import {Quiz} from "../../models/quiz";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  providers: [{ provide: BsDropdownConfig, useValue: { isAnimated: true, autoClose: true } }]
})
export class HeaderComponent implements OnInit {

  private userName: string;
  private subscriptions: Subscription[] = [];

  constructor(private userService: UserService, private quizService: QuizService) { }

  ngOnInit() {
  }

  public isLogin(): boolean {
    if(this.userService.currentUser != null) {
      this.userName = this.userService.currentUser.userName;
      return true;
    }
    return false;
  }

  public logOut(): void {
    this.userService.currentUser = null;
  }

  public findQuizLike(searchParam: string): void {
    this.quizService.currQuizList = null;
    this.subscriptions.push(this.quizService.findQuizLike(searchParam).subscribe(quizList => {
      this.quizService.currQuizList = quizList as Quiz[];
      console.log(this.quizService.currQuizList);
      console.log(this.quizService.currQuizList.length);
    }));
  }
}
