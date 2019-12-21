import {Component, OnInit, TemplateRef} from '@angular/core';
import {User} from "../../../models/user";
import {Subscription} from "rxjs";
import {UserService} from "../../../services/user.service";
import {Template} from "@angular/compiler/src/render3/r3_ast";
import {BsModalRef, BsModalService, PageChangedEvent} from "ngx-bootstrap";
import {PageObject} from "../../../models/pageObject";

@Component({
  selector: 'app-user-editing',
  templateUrl: './user-editing.component.html',
  styleUrls: ['./user-editing.component.css']
})
export class UserEditingComponent implements OnInit {
  private users: User[];
  private subscriptions: Subscription[] = [];
  private modalRef: BsModalRef;
  private curUserId: number;
  private curPage: number = 1;
  private pageSize: number = 12;
  private sortParam: string = null;
  private pageUsers: PageObject;

  //1 - asc, -1 - desc
  private sortFormat: number = 1;

  constructor(private userService: UserService, private modalService: BsModalService) {
  }

  ngOnInit() {
    this.getAllUsers();
  }

  public getAllUsers(): void {
    this.subscriptions.push(this.userService.getAllUsers(this.curPage - 1, this.pageSize, this.sortParam, this.sortFormat).subscribe((users) => {
      this.pageUsers = users as PageObject;
      this.users = this.pageUsers.content;
    }));
  }

  public deleteUser(id: number): void {
    this.subscriptions.push(this.userService.deleteUser(id).subscribe(() => {
      this.getAllUsers();
    }));
    this.modalRef.hide();
  }

  public setSortParam(param: string): void {
    if (this.sortParam === param) {
      this.sortFormat = -this.sortFormat;
    } else {
      this.sortParam = param;
    }
    this.updateUsers();
  }

  public updateUsers(): void {
    this.getAllUsers();
  }

  openModal(template: TemplateRef<any>, curUser: number) {
    this.modalRef = this.modalService.show(template);
    this.curUserId = curUser;
  }

  pageChanged(event: PageChangedEvent) {
    this.curPage = event.page;
    this.getAllUsers();
  }
}
