import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private httpClient: HttpClient) { }

  authenticate(userName: any, password: any){
    let user = {
      username: userName,
      password: password
    }
    console.log(user);
    return this.httpClient.post("http://localhost:8080/user/authenticate",user);
  }

  iseUserLoggedIn(){
    let user = sessionStorage.getItem("username");
    return !(user == null);
  }

  logout(){
    sessionStorage.removeItem("username");
  }
}
