import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {User} from "../register/user";
import {Subscription} from "rxjs";
import {UserService} from "../../services/user.service";
import {CurrentUserService} from "../../services/currentUser.service"

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private subscriptions: Subscription[] = [];
  loginForm: FormGroup;

  constructor(private userService: UserService) {

  }

  ngOnInit() {
    this.loginForm = new FormGroup({
      "userEmail": new FormControl("", [Validators.required, Validators.pattern("[a-zA-Z_.]+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}")]),
      "userPassword": new FormControl("", Validators.required)
    });
  }

  //Method to send email for getting user from db
  public getUserByEmail(email): void {
    this.subscriptions.push(this.userService.getUserByEmail(email).subscribe(user => {
      this.userService.currentUser = user as User;
    }));
  }
}
