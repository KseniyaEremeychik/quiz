import {Component, OnInit, TemplateRef} from '@angular/core';
import {Category} from "../../../models/category";
import {Subscription} from "rxjs";
import {CategoryService} from "../../../services/category.service";
import {BsModalRef, BsModalService} from "ngx-bootstrap";

@Component({
  selector: 'app-category-editing',
  templateUrl: './category-editing.component.html',
  styleUrls: ['./category-editing.component.css']
})
export class CategoryEditingComponent implements OnInit {

  private category: Category = new Category();
  private modalRef: BsModalRef;
  public categories: Category[];
  private subscriptions: Subscription[] = [];
  private sortFormatName: string = 'desc';
  private curCategoryId: number;
  private isValid: boolean = true;
  private errorMessage: string = null;

  constructor(private categoryService: CategoryService,
              private modalService: BsModalService) {
  }

  ngOnInit() {
    this.getAllCategories();
  }

  public getAllCategories(): void {
    this.subscriptions.push(this.categoryService.getAllCategories().subscribe(categories => {
      this.categories = categories as Category[];
    }));
  }

  public getAllSortedCategories(sortParam: string): void {
    if (this.sortFormatName == 'desc') {
      this.sortFormatName = 'asc';
    } else {
      this.sortFormatName = 'desc';
    }
    this.subscriptions.push(this.categoryService.getAllSortedCategories(sortParam, this.sortFormatName).subscribe(sortedCategories => {
      this.categories = sortedCategories as Category[];
    }));
  }

  public deleteCategory(id: number): void {
    this.subscriptions.push(this.categoryService.deleteCategory(id).subscribe(() => {
      this.updateCategories();
    }));
    this.modalRef.hide();
  }

  public addCategory(name: string, template: TemplateRef<any>): void {
    this.validateNewCategory(name);
    if (this.isValid) {
      this.category.name = name;
      this.subscriptions.push(this.categoryService.addCategory(this.category).subscribe(() => {
        this.updateCategories();
      }, error => {
        if (error == null) {
          this.modalRef.hide();
        } else if (error.status == 400) {
          this.errorMessage = 'This category is already exist!';
          this.modalRef = this.modalService.show(template);
        }
      }));
      this.modalRef.hide();
    } else {
      this.errorMessage = 'Invalid format for category name!';
    }
  }

  public updateCategories(): void {
    this.getAllCategories();
  }

  openModalDelete(template: TemplateRef<any>, curCategory: number) {
    this.modalRef = this.modalService.show(template);
    this.curCategoryId = curCategory;
  }

  openModalAdd(template: TemplateRef<any>) {
    this.errorMessage = null;
    this.modalRef = this.modalService.show(template);
  }

  private validateNewCategory(categoryName: string): void {
    let val = true;
    let regEx = new RegExp("^[a-zA-Z0-9!?,._-][a-zA-Z0-9!?,._\\s-]+$");
    if (categoryName.length == 0 || categoryName.length > 100 || !(regEx.test(categoryName))) {
      val = false;
    }
    this.isValid = val;
  }
}
