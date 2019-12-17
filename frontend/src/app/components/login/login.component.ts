import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {User} from "../../models/user";
import {Subscription} from "rxjs";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  @ViewChild('email') email: ElementRef;
  @ViewChild('password') password: ElementRef;

  public userLoginData: User = new User();
  private subscriptions: Subscription[] = [];
  loginForm: FormGroup;
  unauthorizedError: string = null;

  constructor(private userService: UserService, private router: Router) {

  }

  ngOnInit() {
    this.loginForm = new FormGroup({
      "userEmail": new FormControl("", [Validators.required, Validators.pattern("[a-zA-Z_.]+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}")]),
      "userPassword": new FormControl("", [Validators.required, Validators.minLength(8)])
    });

    localStorage.clear();
    this.userService.currentUser = null;
  }

  public findUserByEmail(email: string, password: string): void {
    this.userLoginData.email = email;
    this.userLoginData.password = password;
    this.subscriptions.push(this.userService.findUserByEmail(this.userLoginData).subscribe(userWithToken => {
      this.userService.currentUser = userWithToken.user as User;
      localStorage.setItem("token", userWithToken.token);
      if(this.userService.currentUser.errors == null) {
        this.router.navigate(['/']);
      }
    }, error => {
      if(error.status == 401) {
        this.unauthorizedError = 'Bad credentials';
        this.email.nativeElement.value = '';
        this.password.nativeElement.value = '';
      }
    }));
  }
}
