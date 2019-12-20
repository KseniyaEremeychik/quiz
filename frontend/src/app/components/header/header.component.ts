import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {UserService} from "../../services/user.service";
import {BsDropdownConfig} from "ngx-bootstrap";
import {QuizService} from "../../services/quiz.service";
import {Subscription} from "rxjs";
import {Quiz} from "../../models/quiz";
import {Router} from "@angular/router";
import {PageObject} from "../../models/pageObject";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  providers: [{ provide: BsDropdownConfig, useValue: { isAnimated: true, autoClose: true } }]
})
export class HeaderComponent implements OnInit {
  @ViewChild('search') search: ElementRef;

  private userName: string;
  private subscriptions: Subscription[] = [];

  constructor(private userService: UserService,
              private quizService: QuizService,
              private router: Router) { }

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
    localStorage.clear();
  }

  public findQuizLike(searchParam: string): void {
    localStorage.removeItem("categoryId");
    this.quizService.quizPage = null;
    this.quizService.searchParam = searchParam;
    this.subscriptions.push(this.quizService.findQuizLike(searchParam, 0, 8).subscribe(quizPage => {
      this.quizService.quizPage = quizPage as PageObject;
      this.quizService.currQuizList = this.quizService.quizPage.content;
      this.router.navigate(['/quiz']);
      this.search.nativeElement.value = '';
    }));
  }

  public cleanStatus(): void {
    this.quizService.status = null;
    this.router.navigate(['/quizEditing']);
  }
}
