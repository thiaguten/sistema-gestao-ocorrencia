import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/login/service/login.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(private loginService: LoginService) { }

  ngOnInit(): void {
  }

  get userName(): string {
    if (this.loginService.givenName && this.loginService.familyName) {
      return `${this.loginService.givenName} ${this.loginService.familyName}`;
    }
    return this.loginService.preferredUsername;
  }

  isAuthenticated(): boolean {
    return this.loginService.isLoggedIn();
  }

}
