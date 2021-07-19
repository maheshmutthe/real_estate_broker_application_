import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PropertyService } from 'src/app/service/property.service';

@Component({
  selector: 'app-add-property',
  templateUrl: './add-property.component.html',
  styleUrls: ['./add-property.component.css']
})
export class AddPropertyComponent implements OnInit {

  propConfig: any;
  offerType: any;
  offerCost: any;
  areaSqft: any;
  address: any;
  street: any;
  city: any;

  userId: any;

  constructor(private propertyService: PropertyService, private router: Router) { }

  ngOnInit(): void {
    let user = JSON.parse(sessionStorage.getItem("user")!);
    this.userId = user.userId;
  }

  addProperty(){
    let property = {
      propConfig: this.propConfig,
      offerType: this.offerType,
      offerCost: this.offerCost,
      areaSqft: this.areaSqft,
      address: this.address,
      street: this.street,
      city: this.city
    }
    this.propertyService.addProperty(this.userId,property).subscribe(
      (response) => {
        console.log(response);
        this.router.navigate(['/myproperties']);
      }
    );
  }

}
