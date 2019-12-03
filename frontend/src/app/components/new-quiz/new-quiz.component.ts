/*import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Category} from "../../models/category";
import {Subscription} from "rxjs";
import {CategoryService} from "../../services/category.service";
import { FormBuilder } from '@angular/forms';
import {Question} from '../../models/question';
import {QuestionForNewQuiz} from "../../models/questionForNewQuiz";

@Component({
  selector: 'app-new-quiz',
  templateUrl: './new-quiz.component.html',
  styleUrls: ['./new-quiz.component.css']
})
export class NewQuizComponent implements OnInit {

  public categories: Category[];
  private subscriptions: Subscription[] = [];
  private questions: QuestionForNewQuiz[] = [];
  private quizName = '';

  constructor(private categoryService: CategoryService, private formBuilder: FormBuilder) {
    this.questions.push(
      {text: '', answers: ['', ''], rightAnswer: ''},
      {text: '', answers: ['', ''], rightAnswer: ''}
    );
  }

  ngOnInit() {
    this.getAllCategories();
  }

  private getAllCategories(): void {
    this.subscriptions.push(this.categoryService.getAllCategories().subscribe(categories => {
      this.categories = categories as Category[];
    }));
  }

  onSubmit(data) {
    // Process checkout data here
    console.warn('Your order has been submitted', data);
  }

  onAddAnswer(ind) {
    this.questions[ind].answers.push('');
  }

  /!*onBlurAnswer(value, qesInd, ansInd) {
    console.log(qesInd, ansInd, value);
    this.questions[qesInd].answers[ansInd] = value;
    console.log(this.questions[qesInd].answers);
  }*!/

  onChangeAns(evt, i, j) {
    this.questions[i].answers[j] = evt.target.value;
    console.log(this.questions);
  }

  onAddQuestion() {
    this.questions.push({text: '', answers: ['', ''], rightAnswer: ''})
  }

  onChangeQuizName(name) {
    this.quizName = name;
    console.log(`Quiz name: ${this.quizName}`);
  }

  onChangeQuestionText(i, text) {
    this.questions[i].text = text;
    console.log(this.questions);
  }
}*/

import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Category} from "../../models/category";
import {Subscription} from "rxjs";
import {CategoryService} from "../../services/category.service";
import { FormBuilder } from '@angular/forms';
import {Question} from '../../models/question';
import {QuestionForNewQuiz} from "../../models/questionForNewQuiz";
import {Answer} from "../../models/answer";

@Component({
  selector: 'app-new-quiz',
  templateUrl: './new-quiz.component.html',
  styleUrls: ['./new-quiz.component.css']
})
export class NewQuizComponent implements OnInit {

  public categories: Category[];
  private subscriptions: Subscription[] = [];
  private questions: Question[] = [];
  private quizName = '';

  constructor(private categoryService: CategoryService, private formBuilder: FormBuilder) {
    let newAnswer: Answer = new Answer();
    newAnswer.text = '';
    this.questions.push(
      {id: '', text: '', answers: [newAnswer]}
    );
  }

  ngOnInit() {
    this.getAllCategories();
  }

  private getAllCategories(): void {
    this.subscriptions.push(this.categoryService.getAllCategories().subscribe(categories => {
      this.categories = categories as Category[];
    }));
  }

  onSubmit(data) {
    // Process checkout data here
    console.warn('Your order has been submitted', data);
  }

  onAddAnswer(ind) {
    let newAnswer: Answer = new Answer();
    newAnswer.text = '';
    this.questions[ind].answers.push(newAnswer);
  }

  /*onBlurAnswer(value, qesInd, ansInd) {
    console.log(qesInd, ansInd, value);
    this.questions[qesInd].answers[ansInd] = value;
    console.log(this.questions[qesInd].answers);
  }*/

  onChangeAns(evt, i, j) {
    this.questions[i].answers[j].text = evt.target.value;
    console.log(this.questions);
  }

  onAddQuestion() {
    let newAnswer: Answer = new Answer();
    newAnswer.text = '';
    this.questions.push({id: '', text: '', answers: [newAnswer]})
  }

  onChangeQuizName(name) {
    this.quizName = name;
    console.log(`Quiz name: ${this.quizName}`);
  }

  onChangeQuestionText(i, text) {
    this.questions[i].text = text;
    console.log(this.questions);
  }
}
