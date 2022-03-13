import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DealService } from 'src/app/service/deal.service';
import { PropertyService } from 'src/app/service/property.service';

@Component({
  selector: 'app-list-all-properties',
  templateUrl: './list-all-properties.component.html',
  styleUrls: ['./list-all-properties.component.css']
})
export class ListAllPropertiesComponent implements OnInit {

  properties: any;
  user: any;

  isCustomer: boolean = false;

  constructor(private propService: PropertyService,private router: Router,private dealService: DealService) { }

  ngOnInit(): void {
    this.user = JSON.parse(sessionStorage.getItem("user")!);
    if(this.user.userRole == "Customer"){
      this.isCustomer = true;
    }
    this.initProps();
  }

  initProps(){
    this.propService.findAllProperties().subscribe(
      (response) => {
        this.properties = response;
        console.log(response);
      }
    );
  }

  buy(propIdFront: any){
    let x = confirm("Are you sure you want to buy this property?");
    if(x){
      let dealDetail = {
        propId: propIdFront,
        custId: this.user.userId
      }
      this.dealService.addDeal(dealDetail).subscribe(
        (response) => {
          this.initProps();
        }
      );
    }
  }

  logout(){
    sessionStorage.removeItem("user");
    sessionStorage.removeItem("token");
    this.router.navigate(['/landing']);
  }
}
