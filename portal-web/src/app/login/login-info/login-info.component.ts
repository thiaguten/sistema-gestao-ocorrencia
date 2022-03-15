import { Component, Input, OnInit } from '@angular/core';

import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-login-info',
  templateUrl: './login-info.component.html',
  styleUrls: ['./login-info.component.scss']
})
export class LoginInfoComponent implements OnInit {

  @Input() userProfile?: object;

  constructor(
    public loginService: LoginService
    ) {}

  ngOnInit(): void {
  }

}
