import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BrokerService } from 'src/app/service/broker.service';
import { CustomerService } from 'src/app/service/customer.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  userId: any;
  userName: any;
  userPassword: any;
  userRole: any;
  userMobile: any;
  userEmail: any;
  userCity: any;

  errorBool: boolean = false;

  constructor(private brokerService: BrokerService,private customerService: CustomerService,private router: Router) { }

  ngOnInit(): void {
    let user = JSON.parse(sessionStorage.getItem("user")!);
    this.userId = user.userId;
    this.userName = user.userName;
    this.userMobile = user.userMobile;
    this.userEmail = user.userEmail;
    this.userCity = user.userCity;
    this.userRole = user.userRole;
  }

  edit(){
    let user = {
      userId: this.userId,
      userName: this.userName,
      userPassword: this.userPassword,
      userRole: this.userRole,
      userMobile: this.userMobile,
      userEmail: this.userEmail,
      userCity: this.userCity,
    };

    if(this.userRole == "Broker"){
      this.brokerService.editBroker(user).subscribe(
        (response: any) => {
          sessionStorage.removeItem("user");
          user.userPassword = response.userPassword;
          sessionStorage.setItem("user",JSON.stringify(user));
          console.log(JSON.stringify(user))
          this.router.navigate(['/profile']);
        },
        (errResponse) => {
          this.errorBool = true;
        }
      );
    } else if(this.userRole = "Customer"){
      this.customerService.editCustomer(user).subscribe(
        (response: any) => {
          sessionStorage.removeItem("user");
          user.userPassword = response.userPassword;
          sessionStorage.setItem("user",JSON.stringify(user));
          this.router.navigate(['/profile']);
        },
        (errResponse) => {
          this.errorBool = true;
        }
      );
    } 
  }
}
