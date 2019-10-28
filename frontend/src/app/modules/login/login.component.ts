import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor() {
    this.loginForm = new FormGroup({
      "email": new FormControl("", Validators.required),
      "password": new FormControl("", [Validators.required, Validators.pattern("\"[a-zA-Z_]+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}")])
    });
  }

  ngOnInit() {
  }

  onSubmit() {
    alert(this.loginForm.value);
  }
}
