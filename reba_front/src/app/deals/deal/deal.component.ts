import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from 'src/app/service/customer.service';

@Component({
  selector: 'app-deal',
  templateUrl: './deal.component.html',
  styleUrls: ['./deal.component.css']
})
export class DealComponent implements OnInit {

  properties: any;

  constructor(private customerService: CustomerService, private router: Router) { }

  ngOnInit(): void {
    let user = JSON.parse(sessionStorage.getItem("user")!);
    let userId = user.userId;

    this.customerService.findCustomer(userId).subscribe(
      (response: any) => {
        console.log(response);
        this.properties = response.custProp;
      }
    );
  }

  logout(){
    sessionStorage.removeItem("user");
    sessionStorage.removeItem("token");
    this.router.navigate(['/landing']);
  }

}
