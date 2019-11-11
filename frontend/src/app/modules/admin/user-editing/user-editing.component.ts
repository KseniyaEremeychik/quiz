import { Component, OnInit } from '@angular/core';
import {User} from "../../register/user";
import {Subscription} from "rxjs";
import {UserService} from "../../../services/user.service";

@Component({
  selector: 'app-user-editing',
  templateUrl: './user-editing.component.html',
  styleUrls: ['./user-editing.component.css']
})
export class UserEditingComponent implements OnInit {

  public users: User[];
  private subscriptions: Subscription[] = [];

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.getAllUsers();
  }

  private getAllUsers(): void {
    this.subscriptions.push(this.userService.getAllUsers().subscribe(users => {
      this.users = users as User[];
    }));
  }

  /*private deleteUser(id: string): void {
    this.subscriptions.push(this.userService.deleteUser(id).subscribe(() => {
      this.getAllUsers();
    }));
  }*/
}
