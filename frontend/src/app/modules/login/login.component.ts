import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {User} from "../register/user";
import {Subscription} from "rxjs";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public user:User = new User();
  private subscriptions: Subscription[] = [];

  loginForm: FormGroup;

  constructor(private userService: UserService) {
    this.loginForm = new FormGroup({
      "userEmail": new FormControl("", [Validators.required, Validators.pattern("[a-zA-Z_.]+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}")]),
      "userPassword": new FormControl("", Validators.required)
    });
  }

  ngOnInit() {
  }

  //Method to send email for getting user from db
  public getUserByEmail(email): void {
    this.subscriptions.push(this.userService.getUserByEmail(email).subscribe(user => {
      this.user = user as User;
      localStorage.setItem('userName', user.userName);
      localStorage.setItem('role', user.role);
    }));
  }

  /*public isExist(email, password): boolean {
    if (localStorage.getItem('email') == email && localStorage.getItem('password') == password) {
      return true;
    }
    return false;
  }*/

}
