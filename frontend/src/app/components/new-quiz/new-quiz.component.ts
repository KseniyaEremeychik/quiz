import {Component, ElementRef, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {Category} from "../../models/category";
import {Subscription} from "rxjs";
import {CategoryService} from "../../services/category.service";
import {Question} from '../../models/question';
import {Answer} from "../../models/answer";
import {Quiz} from "../../models/quiz";
import {UserService} from "../../services/user.service";
import {QuizService} from "../../services/quiz.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {BsModalRef, BsModalService} from "ngx-bootstrap";

@Component({
  selector: 'app-new-quiz',
  templateUrl: './new-quiz.component.html',
  styleUrls: ['./new-quiz.component.css']
})
export class NewQuizComponent implements OnInit {
  /*@ViewChild('quizName') quizNameInput: ElementRef;
  @ViewChild('onChangeText') questionInput: ElementRef;
  @ViewChild('answerText') answerInput: ElementRef;*/

  public categories: Category[];
  private subscriptions: Subscription[] = [];
  private quiz: Quiz = new Quiz();
  private questions: Question[] = [];
  private quizName = '';
  private modalRef: BsModalRef;
  private isValid: boolean = true;
  private modalTitle: string = '';
  private modalMessage: string = '';

  constructor(private categoryService: CategoryService,
              private userService: UserService,
              private quizService: QuizService,
              private modalService: BsModalService) {
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
    newQuestion.answers = [this.getNewAnswer(1), this.getNewAnswer(0)];
    return newQuestion;
  }

  getNewAnswer(isRight: number): Answer {
    let newAnswer: Answer = new Answer();
    newAnswer.text = '';
    newAnswer.isRight = isRight;
    return newAnswer;
  }

  onAddAnswer(ind) {
    let newAnswer: Answer = new Answer();
    newAnswer.text = '';
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

  onDeleteAns(i, j): void {
    let isRightSelected = false;
    this.questions[i].answers.forEach(ans => function(ans){
      if(ans.isRight == 1) {
        isRightSelected = true;
      }
    })

    if(isRightSelected) {
      if(j == 0) {
        this.questions[i].answers[j+1].isRight = 1;
      }
    }
    this.questions[i].answers.splice(j, 1);
  }

  onDeleteQuestion(i: number): void {
    this.questions.splice(i, 1);
  }

  public createNewQuiz(categoryName: string, template: TemplateRef<any>): void {
    this.validateQuiz();
    if(this.isValid) {
      this.modalTitle = 'Success';
      /*this.quizNameInput.nativeElement.value = '';
      this.questionInput.nativeElement.value = '';
      this.answerInput.nativeElement.value = '';*/
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
      }, error => {
        if(error.status == 400) {
          this.modalTitle = 'Invalid input format';
          this.modalMessage = 'Please, check entered fields!';
        }
      }));
    } else {
      this.modalTitle = 'Invalid input format';
    }
    this.openModal(template);
  }

  private validateQuiz():void {
    let val = true;
    let result = 'Your quiz has been submitted for moderation';
    let regEx = new RegExp("^[a-zA-Z0-9!?,._-][a-zA-Z0-9!?,._\\s-]+$");
    if (this.quizName.length == 0 || !(regEx.test(this.quizName)) || this.quizName.length > 100) {
      result = 'Please, check the entered quiz name!'
      val = false;
    } else {
      this.questions.forEach(function(question, index) {
        if(question.text.length == 0 || !(regEx.test(question.text)) || question.text.length > 250) {
          result = 'Please, check the entered questions!'
          val = false;
        } else {
          question.answers.forEach(function (answer, index) {
            if(answer.text.length == 0 || !(regEx.test(answer.text)) || answer.text.length > 100) {
              result = 'Please, check the entered answers!'
              val = false;
            }
          });
        }
      });
    }
    this.isValid = val;
    this.modalMessage = result;
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }
}
