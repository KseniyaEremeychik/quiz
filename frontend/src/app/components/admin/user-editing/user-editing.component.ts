import {Component, OnInit, TemplateRef} from '@angular/core';
import {User} from "../../../models/user";
import {Subscription} from "rxjs";
import {UserService} from "../../../services/user.service";
import {Template} from "@angular/compiler/src/render3/r3_ast";
import {BsModalRef, BsModalService} from "ngx-bootstrap";

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

  constructor(private userService: UserService, private modalService: BsModalService) { }

  ngOnInit() {
    this.getAllUsers();
  }

  public getAllUsers(): void {
    this.subscriptions.push(this.userService.getAllUsers().subscribe(users => {
      this.users = users as User[];
    }));
  }

  public deleteUser(id: number): void {
    this.subscriptions.push(this.userService.deleteUser(id).subscribe(() => {
      this.getAllUsers();
    }));
    this.modalRef.hide();
  }

  openModal(template: TemplateRef<any>, curUser: number) {
    this.modalRef = this.modalService.show(template);
    this.curUserId = curUser;
  }
}
