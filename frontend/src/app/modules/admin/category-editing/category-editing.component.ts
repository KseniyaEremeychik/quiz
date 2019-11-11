import { Component, OnInit } from '@angular/core';
import {Category} from "../../category/category";
import {Subscription} from "rxjs";
import {CategoryService} from "../../../services/category.service";

@Component({
  selector: 'app-category-editing',
  templateUrl: './category-editing.component.html',
  styleUrls: ['./category-editing.component.css']
})
export class CategoryEditingComponent implements OnInit {

  public categories: Category[];
  private subscriptions: Subscription[] = [];

  constructor(private categoryService: CategoryService) { }

  ngOnInit() {
    this.getAllCategories();
  }

  public getAllCategories(): void {
    this.subscriptions.push(this.categoryService.getAllCategories().subscribe(categories => {
      this.categories = categories as Category[];
    }));
  }
}
