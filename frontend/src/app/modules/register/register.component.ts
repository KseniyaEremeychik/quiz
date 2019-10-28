import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;

  constructor() {
    this.registerForm = new FormGroup({
      "userName": new FormControl("", [Validators.required, Validators.minLength(5)]),
      "userEmail": new FormControl("", [Validators.required, Validators.pattern("[a-zA-Z_.]+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}")]),
      "userPassword": new FormControl("", Validators.required),
      "userPassword2": new FormControl("", Validators.required)
    });
  }

  ngOnInit() {
  }

  submit() {
    console.log(this.registerForm);
  }
}
