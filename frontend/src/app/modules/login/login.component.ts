import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {User} from "../register/user";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public user:User = new User();
  loginForm: FormGroup;

  constructor() {
    this.loginForm = new FormGroup({
      "userEmail": new FormControl("", [Validators.required, Validators.pattern("[a-zA-Z_.]+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}")]),
      "userPassword": new FormControl("", Validators.required)
    });
  }

  ngOnInit() {
  }

  public getAll(): void {

  }

  public isExist(email, password): boolean {
    if (localStorage.getItem('email') == email && localStorage.getItem('password') == password) {
      return true;
    }
    return false;
  }

}
