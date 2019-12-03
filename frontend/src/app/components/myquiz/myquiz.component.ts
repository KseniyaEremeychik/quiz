import { Component, OnInit } from '@angular/core';
import {Quiz} from "../../models/quiz";
import {QuizService} from "../../services/quiz.service";
import {Subscription} from "rxjs";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-myquiz',
  templateUrl: './myquiz.component.html',
  styleUrls: ['./myquiz.component.css']
})
export class MyquizComponent implements OnInit {
  private quizList: Quiz[] = [];
  private subscriptions: Subscription[] = [];

  constructor(private quizService: QuizService, private userService: UserService) { }

  ngOnInit() {
    this.getAllUsersQuiz();
  }

  public getAllUsersQuiz(): void {
    this.subscriptions.push(this.quizService.getAllQuizByUserId(this.userService.currentUser.id).subscribe((quiz) => {
      this.quizList = quiz as Quiz[];
    }));
  }

  public deleteQuiz(id: string): void {
    /*this.subscriptions.push(this.quizService.deleteQuiz(id).subscribe(() => {

    }));*/
  }
}
