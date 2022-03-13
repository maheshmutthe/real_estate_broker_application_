import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../service/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  userName: any;
  userPassword: any;

  errorBool: boolean = false;

  constructor(private router: Router, private authService: AuthenticationService) { }

  ngOnInit(): void {
  }

  login(){
    sessionStorage.removeItem("token");
    sessionStorage.removeItem("user");
    this.authService.authenticate(this.userName,this.userPassword).subscribe(
      (userData: any) => {
        console.log(userData);
        //console.log(userData.user.username);
        //console.log(userData.user);
        sessionStorage.setItem("user",JSON.stringify(userData.user));
        let tokenStr = "Bearer " + userData.token;
        sessionStorage.setItem("token",tokenStr);
        console.log("Token: " + tokenStr);
        this.router.navigate(['/properties']);
      },
      (errorResp) => {
        this.errorBool = true;
      }
    );
  }

}
