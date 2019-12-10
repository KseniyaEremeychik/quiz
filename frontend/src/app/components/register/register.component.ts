import {Component, OnInit, TemplateRef} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {User} from "../../models/user";
import {Subscription} from "rxjs";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
import {BsModalRef, BsModalService} from "ngx-bootstrap";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  private user: User = new User();
  private modalRef: BsModalRef;
  private subscriptions: Subscription[] = []

  registerForm: FormGroup;

  constructor(private userService: UserService, private router: Router, private modalService: BsModalService) {

  }

  ngOnInit() {
    this.registerForm = new FormGroup({
      "userName": new FormControl("", [Validators.required, Validators.minLength(4), Validators.pattern("[a-zA-Z_]+")]),
      "userEmail": new FormControl("", [Validators.required, Validators.pattern("[a-zA-Z_.]+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}")]),
      "userPassword": new FormControl("", [Validators.required, Validators.minLength(8)]),
      "userPassword2": new FormControl("", [Validators.required, Validators.minLength(8)])
      /*"userName": new FormControl("", []),
      "userEmail": new FormControl("", []),
      "userPassword": new FormControl("", []),
      "userPassword2": new FormControl("", [])*/
    });
  }

  public addUser(username, email, password, template: TemplateRef<any>): void {
    this.user.userName = username
    this.user.email = email
    this.user.password = password
    this.user.role = 'user'

    this.subscriptions.push(this.userService.saveUser(this.user).subscribe((user) => {
      this.userService.userForRegister = user as User;
      if(this.userService.userForRegister.errors != null) {
        this.router.navigate(['/register']);
      }
      else {
        this.router.navigate(['/login']);
        this.modalRef = this.modalService.show(template);
      }
    }));
  }

}
