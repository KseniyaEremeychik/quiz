import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  private userName: string;

  constructor() { }

  ngOnInit() {
  }

  public isLogin(): boolean {
    if(localStorage.getItem('userName') != null) {
      this.userName = localStorage.getItem('userName');
      return true;
    }
    return false;
  }

  public logOut(): void {
    localStorage.clear();
  }
}
