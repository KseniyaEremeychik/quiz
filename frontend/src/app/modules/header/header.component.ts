import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {CurrentUserService} from "../../services/currentUser.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  private userName: string;

  constructor(private userService: UserService) { }

  ngOnInit() {
  }

  public isLogin(): boolean {
    if(this.userService.currentUser != null) {
      this.userName = this.userService.currentUser.userName;
      return true;
    }
    return false;
  }

  public logOut(): void {
    this.userService.currentUser = null;
  }
}
