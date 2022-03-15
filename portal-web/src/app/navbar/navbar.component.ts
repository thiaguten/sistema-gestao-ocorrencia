import { Component, Input, OnInit } from '@angular/core';

import { LoginService } from '../login/service/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  @Input() userProfile?: object;

  constructor(private loginService: LoginService) { }

  ngOnInit(): void {
  }

  entrar(): void {
    this.loginService.login();
  }

  sair(): void {
    this.loginService.logout();
  }
}
