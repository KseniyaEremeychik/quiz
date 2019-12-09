import { Component, OnInit } from '@angular/core';
import {QuizService} from "../../services/quiz.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {Subscription} from "rxjs";
import {AnswerService} from "../../services/answer.service";
import {RightAnswers} from "../../models/rightAnswers";

@Component({
  selector: 'app-quiz-passing',
  templateUrl: './quiz-passing.component.html',
  styleUrls: ['./quiz-passing.component.css']
})
export class QuizPassingComponent implements OnInit {
  private request = {};
  private userAnswers: Map<number, number> = new Map<number, number>();
  private subscriptions: Subscription[] = [];
  private rightAnswers: RightAnswers = null;

  constructor(private quizService: QuizService, private answerService: AnswerService) { }

  ngOnInit() {
  }

  public onChange(questionId: number, answerId: number): void {
    this.userAnswers.set(questionId, answerId);
  }

  public changeFormat(): void {
    this.userAnswers.forEach((value, key) => {
      this.request[key] = value;
    })
  }

  public finishPassing(): void {
    this.changeFormat();
    this.rightAnswers = null;
    this.subscriptions.push(this.answerService.getRightAnswer(this.request).subscribe((rightAnswersModel) => {
        this.rightAnswers = rightAnswersModel as RightAnswers;
    }));
  }
}
