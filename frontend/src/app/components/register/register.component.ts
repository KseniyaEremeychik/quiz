import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {User} from "../../models/user";
import {Subscription} from "rxjs";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public user: User = new User();
  private subscriptions: Subscription[] = []

  registerForm: FormGroup;

  constructor(private userService: UserService) {

  }

  ngOnInit() {
    this.registerForm = new FormGroup({
      "userName": new FormControl("", [Validators.required, Validators.minLength(4)]),
      "userEmail": new FormControl("", [Validators.required, Validators.pattern("[a-zA-Z_.]+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}")]),
      "userPassword": new FormControl("", Validators.required),
      "userPassword2": new FormControl("", Validators.required)
    });
  }

  public addUser(username, email, password): void {
    this.user.userName = username
    this.user.email = email
    this.user.password = password
    this.user.role = 'user'

    this.subscriptions.push(this.userService.saveUser(this.user).subscribe(() => {

    }));
  }

}