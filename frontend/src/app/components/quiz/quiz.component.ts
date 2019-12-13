import { Component, OnInit } from '@angular/core';
import {QuizService} from "../../services/quiz.service";
import {Subscription} from "rxjs";
import {Quiz} from "../../models/quiz";
import {Question} from "../../models/question";

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.css']
})
export class QuizComponent implements OnInit {
  private subscriptions: Subscription[] = [];
  private questions: Question[];

  constructor(private quizService: QuizService) { }

  ngOnInit() {
    if(localStorage.getItem("categoryId")) {
      this.quizService.currQuizList = null;
      this.subscriptions.push(this.quizService.findAllQuizByCategoryId(+localStorage.getItem("categoryId")).subscribe(quiz => {
        this.quizService.currQuizList = quiz as Quiz[];
      }));
    }
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
}
