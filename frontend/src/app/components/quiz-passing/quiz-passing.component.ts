import { Component, OnInit } from '@angular/core';
import {QuizService} from "../../services/quiz.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {Subscription} from "rxjs";
import {AnswerService} from "../../services/answer.service";
import {RightAnswers} from "../../models/rightAnswers";
import {InfoForStat} from "../../models/infoForStat";
import {UserService} from "../../services/user.service";
import {StatisticsService} from "../../services/statistics.service";

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
  private infoForStat: InfoForStat = new InfoForStat();

  constructor(private quizService: QuizService,
              private answerService: AnswerService,
              private userService: UserService,
              private statisticService: StatisticsService) { }

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

    this.infoForStat.userId = this.userService.currentUser.id;
    this.infoForStat.quizId = this.quizService.currQuiz.id;
    let today = new Date();
    let dd = String(today.getDate()).padStart(2, '0');
    let mm = String(today.getMonth() + 1).padStart(2, '0');
    let yyyy = String(today.getFullYear());
    this.infoForStat.passageDate = yyyy + '-' + mm + '-' + dd;

    this.subscriptions.push(this.answerService.getRightAnswer(this.request).subscribe((rightAnswersModel) => {
      this.rightAnswers = rightAnswersModel as RightAnswers;
      this.infoForStat.rightAnswersPercent = this.rightAnswers.percent;
      this.subscriptions.push(this.statisticService.addNewStatistic(this.infoForStat).subscribe((savedStat) => {
        
      }));
    }));

  }
}
