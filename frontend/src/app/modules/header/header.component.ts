import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {BsDropdownConfig} from "ngx-bootstrap";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  providers: [{ provide: BsDropdownConfig, useValue: { isAnimated: true, autoClose: true } }]
})
export class HeaderComponent implements OnInit {

  private userName: string;

  constructor(private userService: UserService) { }

  ngOnInit() {
  }

  public isLogin(): boolean {
    if(this.userService.currentUser != null) {
      this.userName = this.userService.currentUser.userName;
      console.log(this.userService.currentUser.id);
      return true;
    }
    return false;
  }

  public logOut(): void {
    this.userService.currentUser = null;
  }
}
