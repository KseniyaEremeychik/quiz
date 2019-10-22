import { Component, OnInit } from '@angular/core';
import { Category } from "./category";
import {CategoryService} from "../../services/category.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {BsModalService} from "ngx-bootstrap";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public categories: Category[];
  private subscriptions: Subscription[] = [];

  constructor(private categoryService: CategoryService,
              private loadingService: Ng4LoadingSpinnerService,
              private modalService: BsModalService) { //to show the modal, we also need the ngx-bootstrap service
  }

  ngOnInit() {
    this.getCategories();
  }

  private getCategories(): void {
    this.loadingService.show();
    // Get data from CategoryService
    this.subscriptions.push(this.categoryService.getCategories().subscribe(categories => {
      // Parse json response into local array
      this.categories = categories as Category[];
      // Check data in console
      console.log(this.categories);// don't use console.log in angular :)
      this.loadingService.hide();
    }));
  }

}
