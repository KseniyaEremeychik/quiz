import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Category} from "../../models/category";
import {Subscription} from "rxjs";
import {CategoryService} from "../../services/category.service";
import { FormBuilder } from '@angular/forms';
import {Question} from '../../models/question';

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
  checkoutForm;

  constructor(private categoryService: CategoryService, private formBuilder: FormBuilder) {
    this.questions.push(
      {text: '', answers: ['', ''], rightAnswer: ''},
      {text: '', answers: ['', ''], rightAnswer: ''}
    );
    this.checkoutForm = this.formBuilder.group({
      name: '',
      category: '',
      questions: this.formBuilder.group({
        text: '',
        answers: this.formBuilder.group({
          ans1: '',
          ans2: '',
        }),
        rightAnswer: '',
      })
    });
  }

  ngOnInit() {
    this.getAllCategories();
  }

  private getAllCategories(): void {
    // Get data from CategoryService
    this.subscriptions.push(this.categoryService.getAllCategories().subscribe(categories => {
      // Parse json response into local array
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

  onAddQuestion() {
    this.questions.push({text: '', answers: ['', ''], rightAnswer: ''})
  }

  onBlurQuizName(name) {
    this.quizName = name;
  }
}
