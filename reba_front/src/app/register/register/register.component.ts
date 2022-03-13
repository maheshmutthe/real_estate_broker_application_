import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BrokerService } from '../../service/broker.service';
import { CustomerService } from '../../service/customer.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  userName: any;
  userPassword: any;
  userRole: any;
  userMobile: any;
  userEmail: any;
  userCity: any;

  errorBool: boolean = false;

  constructor(private brokerService: BrokerService, private customerService: CustomerService,private router: Router) { }

  ngOnInit(): void {
  }

  register(){
    let user = {
      userName: this.userName,
      userPassword: this.userPassword,
      userRole: this.userRole,
      userMobile: this.userMobile,
      userEmail: this.userEmail,
      userCity: this.userCity
    };
    console.log(user);
    if(this.userRole == "Broker"){
      console.log("Registered as Broker");
      this.brokerService.addBroker(user).subscribe(
        (response) => {
          console.log(response);
          this.router.navigate(['/login']);
        },
        (errResponse) => {
          console.log("User name already taken");
          this.errorBool = true;
        }
      );
    } else if(this.userRole == "Customer"){
      console.log("Registered as Customer");
      this.customerService.addCustomer(user).subscribe(
        (response) => {
          console.log(response);
          this.router.navigate(['/login']);
        },
        (errResponse) => {
          console.log("User name already taken");
          this.errorBool = true;
        }
      );
    }
  }
}
