import {Component, OnInit, TemplateRef} from '@angular/core';
import {Category} from "../../category/category";
import {Subscription} from "rxjs";
import {CategoryService} from "../../../services/category.service";
import {BsModalRef, BsModalService} from "ngx-bootstrap";

@Component({
  selector: 'app-category-editing',
  templateUrl: './category-editing.component.html',
  styleUrls: ['./category-editing.component.css']
})
export class CategoryEditingComponent implements OnInit {

  public modalRef: BsModalRef;
  public categories: Category[];
  private subscriptions: Subscription[] = [];

  constructor(private categoryService: CategoryService, private modalService: BsModalService) { }

  ngOnInit() {
    this.getAllCategories();
  }

  public getAllCategories(): void {
    this.subscriptions.push(this.categoryService.getAllCategories().subscribe(categories => {
      this.categories = categories as Category[];
    }));
  }

  public deleteCategory(id: string): void {
    this.subscriptions.push(this.categoryService.deleteCategory(id).subscribe(() => {
      this.getAllCategories();
    }));
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }
}
