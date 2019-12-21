import {Component, OnInit} from '@angular/core';
import {Category} from "../../models/category";
import {Subscription} from "rxjs";
import {CategoryService} from "../../services/category.service";
import {Quiz} from "../../models/quiz";
import {QuizService} from "../../services/quiz.service";
import {Router} from "@angular/router";
import {PageObject} from "../../models/pageObject";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  public categories: Category[];
  private subscriptions: Subscription[] = [];

  constructor(private categoryService: CategoryService,
              private quizService: QuizService,
              private router: Router) {
  }

  ngOnInit() {
    this.getAllCategories();
  }

  private getAllCategories(): void {
    this.subscriptions.push(this.categoryService.getAllCategories().subscribe(categories => {
      this.categories = categories as Category[];
    }));
  }

  public findAllQuizByCategoryId(id: number): void {
    this.quizService.searchParam = null;
    localStorage.setItem("categoryId", '' + id);
    this.quizService.currQuizList = null;
    this.subscriptions.push(this.quizService.getQuizByPageAndStatus(id, 0, 8, 'approved').subscribe(resp => {
      this.quizService.quizPage = resp as PageObject;
      this.quizService.currQuizList = this.quizService.quizPage.content;
    }));
  }
}
