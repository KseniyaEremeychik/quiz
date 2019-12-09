import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Category} from "../../models/category";
import {Subscription} from "rxjs";
import {CategoryService} from "../../services/category.service";
import {Question} from '../../models/question';
import {Answer} from "../../models/answer";
import {Quiz} from "../../models/quiz";
import {UserService} from "../../services/user.service";
import {QuizService} from "../../services/quiz.service";

@Component({
  selector: 'app-new-quiz',
  templateUrl: './new-quiz.component.html',
  styleUrls: ['./new-quiz.component.css']
})
export class NewQuizComponent implements OnInit {

  public categories: Category[];
  private subscriptions: Subscription[] = [];
  private quiz: Quiz = new Quiz();
  private questions: Question[] = [];
  private quizName = '';

  constructor(private categoryService: CategoryService, private userService: UserService, private quizService: QuizService) {
    this.questions.push(
      this.getNewQuestion()
    );
  }

  ngOnInit() {
    this.getAllCategories();
  }

  public getAllCategories(): void {
    this.subscriptions.push(this.categoryService.getAllCategories().subscribe(categories => {
      this.categories = categories as Category[];
    }));
  }

  getNewQuestion(): Question {
    let newQuestion: Question = new Question();
    newQuestion.text = '';
    newQuestion.answers = [this.getNewAnswer()];
    return newQuestion;
  }

  getNewAnswer(): Answer {
    let newAnswer: Answer = new Answer();
    newAnswer.text = '';
    newAnswer.ordering = 1;
    newAnswer.isRight = 0;
    return newAnswer;
  }

  onAddAnswer(ind) {
    let order = this.questions[ind].answers.length + 1;
    let newAnswer: Answer = new Answer();
    newAnswer.text = '';
    newAnswer.ordering = order;
    newAnswer.isRight = 0;
    this.questions[ind].answers.push(newAnswer);
  }

  onChangeAns(evt, i, j) {
    this.questions[i].answers[j].text = evt.target.value;
  }

  onAddQuestion() {
    this.questions.push(this.getNewQuestion())
  }

  onChangeQuizName(name) {
    this.quizName = name;
  }

  onChangeQuestionText(i, text) {
    this.questions[i].text = text;
  }

  onChangeRightAnswer(i, k) {
    this.questions[i].answers.forEach(ans => ans.isRight = 0);
    this.questions[i].answers[k-1].isRight = 1;
  }

  public createNewQuiz(categoryName: string): void {
    let curCategory = this.categories.filter(function(category) {
      if(category.name == categoryName) {
        return category;
      }
    })

    this.quiz.categoryId = curCategory[0].id;
    this.quiz.name = this.quizName;
    this.quiz.questionNumber = this.questions.length;
    this.quiz.isConfirmed = 'unseen';

    let today = new Date();
    let dd = String(today.getDate()).padStart(2, '0');
    let mm = String(today.getMonth() + 1).padStart(2, '0');
    let yyyy = String(today.getFullYear());

    this.quiz.creationDate = yyyy + '-' + mm + '-' + dd;
    this.quiz.userId = this.userService.currentUser.id;
    this.quiz.questions = this.questions;

    this.subscriptions.push(this.quizService.saveNewQuiz(this.quiz).subscribe((newQuiz) => {
      let q = newQuiz as Quiz;
      console.log(q);
    }));
  }
}
