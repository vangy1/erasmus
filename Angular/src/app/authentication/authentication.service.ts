import {Injectable} from '@angular/core';
import {Authentication} from "./authentication";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  authentication: Authentication;

  constructor(private http: HttpClient) {
    this.checkIfSignedIn();
  }

  signOut() {
    this.http.post(environment.apiUrl + '/logout', null, {
      withCredentials: true
    }).subscribe(() => {
      this.authentication = Authentication.GUEST;
    });
  }

  checkIfSignedIn() {
    this.http.get(environment.apiUrl + '/authentication/info', {
      withCredentials: true,
      responseType: 'text'
    }).subscribe((authentication) => {
      this.authentication = Authentication[authentication];
    });
  }
}
