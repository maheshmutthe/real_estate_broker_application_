import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BrokerService } from 'src/app/service/broker.service';
import { CustomerService } from 'src/app/service/customer.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user: any;
  
  constructor(private brokerService: BrokerService,private customerService: CustomerService, private router: Router) { }

  ngOnInit(): void {
    console.log(JSON.parse(sessionStorage.getItem("user")!));
    this.user = JSON.parse(sessionStorage.getItem("user")!);
  }

  confirmDelete(){
    let c = confirm("Are you sure you want to delete your account?\n You will lose all your data once you delete.");
    if(c == true){
      this.deleteUser();
    }
  }

  deleteUser(){
    if(this.user.userRole == "Broker"){
      this.brokerService.deleteBroker(this.user.userId).subscribe(
        (response) => {
          console.log(response);
          sessionStorage.removeItem("user");
          sessionStorage.removeItem("token");
          this.router.navigate(['/landing']);
        }
      );
    } else if(this.user.userRole == "Customer"){
      this.customerService.deleteCustomer(this.user.userId).subscribe(
        (response) => {
          console.log(response);
          sessionStorage.removeItem("user");
          sessionStorage.removeItem("token");
          this.router.navigate(['/landing']);
        }
      )
    }
  }

  logout(){
    sessionStorage.removeItem("user");
    sessionStorage.removeItem("token");
    this.router.navigate(['/landing']);
  }

}
