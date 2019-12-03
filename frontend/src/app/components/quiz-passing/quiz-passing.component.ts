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
  private userAnswers: Map<string, string> = new Map<string, string>();
  private subscriptions: Subscription[] = [];

  constructor(private quizService: QuizService, private answerService: AnswerService) { }

  ngOnInit() {
  }

  public onChange(questionId: string, answerId: string): void {
    this.userAnswers.set(questionId, answerId);
  }

  public changeFormat(): void {
    this.userAnswers.forEach((value, key) => {
      this.request[key] = value;
    })
  }

  public finishPassing(): void {
    this.changeFormat();
    this.subscriptions.push(this.answerService.getRightAnswer(this.request).subscribe((rightAnswersModel) => {
        let rightAnswers = rightAnswersModel as RightAnswers;
        console.log(rightAnswers);
    }));
  }
}
