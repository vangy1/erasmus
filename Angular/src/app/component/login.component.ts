import {Component, OnInit} from '@angular/core';
import {LoginService} from "../service/login.service";
import {AuthenticationService} from "../authentication/authentication.service";
import {HttpResponse} from "@angular/common/http";
import {Authentication} from "../authentication/authentication";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  username = "";
  password = "";
  authFail = false;

  constructor(public loginService: LoginService, public authenticationService: AuthenticationService) {
  }

  ngOnInit() {
  }

  login() {
    this.loginService.sendLoginRequest(this.username, this.password).subscribe((res: HttpResponse<any>) => {
      let authentication = res.headers.get('Authentication');
      this.authenticationService.authentication = Authentication[authentication];
    }, (error1 => {
      this.authFail = true;
      console.log(error1)
    }));
  }
}
