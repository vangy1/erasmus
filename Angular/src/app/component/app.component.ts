import {Component} from '@angular/core';
import {AuthenticationService} from "../authentication/authentication.service";
import {Authentication} from "../authentication/authentication";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  authentication = Authentication;

  constructor(public authenticationService: AuthenticationService) {
  }
}
