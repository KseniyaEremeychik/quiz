import { Component, OnInit } from '@angular/core';
import {Category} from "../category/category";
import {Subscription} from "rxjs";
import {CategoryService} from "../../services/category.service";

@Component({
  selector: 'app-new-quiz',
  templateUrl: './new-quiz.component.html',
  styleUrls: ['./new-quiz.component.css']
})
export class NewQuizComponent implements OnInit {

  public categories: Category[];
  private subscriptions: Subscription[] = [];

  constructor(private categoryService: CategoryService) { }

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
}
