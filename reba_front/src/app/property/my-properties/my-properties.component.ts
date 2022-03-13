import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BrokerService } from 'src/app/service/broker.service';
import { PropertyService } from 'src/app/service/property.service';

@Component({
  selector: 'app-my-properties',
  templateUrl: './my-properties.component.html',
  styleUrls: ['./my-properties.component.css']
})
export class MyPropertiesComponent implements OnInit {

  userName: any;
  userPassword: any;

  properties: any;

  constructor(private brokerService: BrokerService, private router: Router, private propertyService: PropertyService) { }

  ngOnInit(): void {
    let user = JSON.parse(sessionStorage.getItem("user")!);
    let userId = user.userId;
    this.initProps(userId);
  }

  initProps(userId: any){
    this.brokerService.findBroker(userId).subscribe(
      (response: any) => {
        console.log(response);
        this.properties = response.broProp;
      }
    );
  }

  edit(propIdFront: any,property: any){
    sessionStorage.setItem("property",JSON.stringify(property));
    sessionStorage.setItem("propId",propIdFront);
    this.router.navigate(['/editProp']);
  }

  delete(propIdFront: any){
    let c = confirm("Are you sure you want to delete this property?");
    if(c){
      let user = JSON.parse(sessionStorage.getItem("user")!);
      let userId = user.userId;
      this.propertyService.deleteProperty(propIdFront).subscribe(
        (response) => {
          this.initProps(userId);
        },
        (errResponse) => {
          alert("Property cannot be deleted as it is already sold");
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
