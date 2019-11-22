import { Component, OnInit } from '@angular/core';
import {QuizService} from "../../services/quiz.service";

@Component({
  selector: 'app-quiz-passing',
  templateUrl: './quiz-passing.component.html',
  styleUrls: ['./quiz-passing.component.css']
})
export class QuizPassingComponent implements OnInit {
  private selectedAnswers: string[]

  constructor(private quizService: QuizService) { }

  ngOnInit() {
  }

  public radioChangeHandler(event: any) {
    this.selectedAnswers.push(event.target.value);
    console.log(this.selectedAnswers);
  }
}
