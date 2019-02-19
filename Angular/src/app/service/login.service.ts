import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor(private http: HttpClient) {
  }

  public sendLoginRequest(username: string, password: string) {
    const formData = new FormData();
    formData.append('username', username);
    formData.append('password', password);

    const httpOptions = {
      observe: 'response' as 'response',
      withCredentials: true
    };

    return this.http.post(environment.apiUrl + '/authentication', formData, httpOptions)
  }
}
