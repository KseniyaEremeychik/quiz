import { Component, OnInit } from '@angular/core';
import {Category} from "../../models/category";
import {Subscription} from "rxjs";
import {CategoryService} from "../../services/category.service";
import {Quiz} from "../../models/quiz";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  public categories: Category[];
  public quizList: Quiz[];
  private subscriptions: Subscription[] = [];

  constructor(private categoryService: CategoryService) {
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

  public findAllQuizByCategoryId(id: string): void {
    this.subscriptions.push(this.categoryService.findAllQuizByCategoryId(id).subscribe(quiz => {
      this.quizList = quiz as Quiz[];
      console.log(this.quizList);
    }));
  }
}
