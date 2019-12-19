import { Component, OnInit } from '@angular/core';
import {PageQuiz} from "../../../models/pageQuiz";
import {QuizService} from "../../../services/quiz.service";
import {Subscription} from "rxjs";
import {Quiz} from "../../../models/quiz";
import {PageChangedEvent} from "ngx-bootstrap";

@Component({
  selector: 'app-quiz-editing',
  templateUrl: './quiz-editing.component.html',
  styleUrls: ['./quiz-editing.component.css']
})
export class QuizEditingComponent implements OnInit {
  private pageQuiz: PageQuiz;
  private quizList: Quiz[] = []
  private subscriptions: Subscription[] = [];
  private curPage: number = 1;
  private pageSize: number = 9;

  constructor(private quizService: QuizService) { }

  ngOnInit() {
    if(this.quizService.status) {
      this.getQuizByStatus(this.quizService.status);
    } else {
      this.getAllQuiz(this.curPage, this.pageSize);
    }
  }

  public getAllQuiz(page: number, size: number): void {
    this.subscriptions.push(this.quizService.getAllQuiz(this.curPage-1, this.pageSize).subscribe(resp => {
      this.pageQuiz = resp as PageQuiz;
      this.quizList = this.pageQuiz.content;
    }));
  }

  public getQuiz(quizId: number, quizName: string): void {
    localStorage.setItem("quizId", '' + quizId);
    localStorage.setItem("quizName", quizName);
  }

  public getQuizByStatus(status: string) {
    if(!(this.quizService.status === status)) {
      this.curPage = 1;
    }
    this.quizService.status = status;
      this.subscriptions.push(this.quizService.getAllQuizWithStatus(status, this.curPage-1, this.pageSize).subscribe(resp => {
      this.pageQuiz = resp as PageQuiz;
      this.quizList = this.pageQuiz.content;
    }));
  }

  pageChanged(event: PageChangedEvent) {
    this.curPage = event.page;
    if(this.quizService.status) {
      this.getQuizByStatus(this.quizService.status);
    } else {
      this.getAllQuiz(this.curPage-1, this.pageSize);
    }
  }
}
