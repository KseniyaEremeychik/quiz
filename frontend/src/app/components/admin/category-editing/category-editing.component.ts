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
  private sortFormatId: string = 'desc';
  private sortFormatName: string = 'desc';
  private curCategoryId: string;

  constructor(private categoryService: CategoryService, private modalService: BsModalService) { }

  ngOnInit() {
    this.getAllCategories();
  }

  public getAllCategories(): void {
    this.subscriptions.push(this.categoryService.getAllCategories().subscribe(categories => {
      this.categories = categories as Category[];
    }));
  }

  public getAllSortedCategories(sortParam: string): void {
    if(sortParam == 'name') {
      if(this.sortFormatName == 'desc') {
        this.sortFormatName = 'asc';
      } else {
        this.sortFormatName = 'desc';
      }
      this.subscriptions.push(this.categoryService.getAllSortedCategories(sortParam, this.sortFormatName).subscribe(sortedCategories => {
        this.categories = sortedCategories as Category[];
      }));
    } else {
      if(this.sortFormatId == 'desc') {
        this.sortFormatId = 'asc';
      } else {
        this.sortFormatId = 'desc';
      }
      this.subscriptions.push(this.categoryService.getAllSortedCategories(sortParam, this.sortFormatId).subscribe(sortedCategories => {
        this.categories = sortedCategories as Category[];
      }));
    }
  }

  public deleteCategory(id: string): void {
    this.subscriptions.push(this.categoryService.deleteCategory(id).subscribe(() => {
      this.updateCategories();
    }));
    this.modalRef.hide();
  }

  public addCategory(name: string): void {
    this.category.name = name;
    this.subscriptions.push(this.categoryService.addCategory(this.category).subscribe(() => {
      this.updateCategories();
    }));
    this.modalRef.hide();
  }

  public updateCategories(): void {
    this.getAllCategories();
  }

  openModalDelete(template: TemplateRef<any>, curCategory: string) {
    this.modalRef = this.modalService.show(template);
    this.curCategoryId = curCategory;
  }

  openModalAdd(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }
}
