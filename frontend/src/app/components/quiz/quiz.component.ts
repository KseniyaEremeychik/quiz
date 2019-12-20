import { Component, OnInit } from '@angular/core';
import {QuizService} from "../../services/quiz.service";
import {Subscription} from "rxjs";
import {Quiz} from "../../models/quiz";
import {Question} from "../../models/question";
import {PageObject} from "../../models/pageObject";
import {PageChangedEvent} from "ngx-bootstrap";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.css']
})
export class QuizComponent implements OnInit {
  private subscriptions: Subscription[] = [];
  private questions: Question[];
  private curPage: number = 1;
  private pageSize: number = 8;
  private buttonText: string = '';

  constructor(private quizService: QuizService,
              private userService: UserService) {
  }

  ngOnInit() {
    if(localStorage.getItem("categoryId")) {
      this.getAllQuiz(+localStorage.getItem("categoryId"), this.curPage, this.pageSize);
    }

    if(this.userService.currentUser.role === 'admin') {
      this.buttonText = 'View quiz';
    } else {
      this.buttonText = 'Start quiz';
    }
  }

  public getAllQuiz(categoryId: number, page: number, size: number): void {
    this.subscriptions.push(this.quizService.getQuizByPageAndStatus(categoryId, this.curPage-1, this.pageSize, 'approved').subscribe(resp => {
      this.quizService.quizPage = resp as PageObject;
      this.quizService.currQuizList = this.quizService.quizPage.content;
    }));
  }

  public getQuizById(quiz: Quiz): void {
    localStorage.setItem("quizId", '' + quiz.id);
    localStorage.setItem("quizName", '' + quiz.name);
    localStorage.setItem("userName", '' + quiz.userName);
    this.quizService.currQuiz = null;
    this.subscriptions.push(this.quizService.getQuizById(quiz.id).subscribe(questions => {
      this.quizService.currQuiz = quiz;
      this.questions = questions as Question[];
      this.quizService.currQuiz.questions = this.questions;
    }));
  }

  pageChanged(event: PageChangedEvent) {
    this.curPage = event.page;
    if(localStorage.getItem("categoryId")) {
      this.getAllQuiz(+localStorage.getItem("categoryId"), this.curPage-1, this.pageSize);
    } else {
      this.getQuizLike(this.quizService.searchParam, this.curPage-1, this.pageSize);
    }
  }

  public getQuizLike(searchParam: string, page: number, size: number): void {
    this.subscriptions.push(this.quizService.findQuizLike(searchParam, page, size).subscribe(quizPage => {
      this.quizService.quizPage = quizPage as PageObject;
      this.quizService.currQuizList = this.quizService.quizPage.content;
    }));
  }
}
