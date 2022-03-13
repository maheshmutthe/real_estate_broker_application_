import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PropertyService } from 'src/app/service/property.service';

@Component({
  selector: 'app-edit-property',
  templateUrl: './edit-property.component.html',
  styleUrls: ['./edit-property.component.css']
})
export class EditPropertyComponent implements OnInit {

  propConfig: any;
  offerType: any;
  offerCost: any;
  areaSqft: any;
  address: any;
  street: any;
  city: any;

  property: any;

  constructor(private propertyService: PropertyService, private router: Router) { }

  ngOnInit(): void {
    this.property = JSON.parse(sessionStorage.getItem("property")!);
    console.log(this.property);
    this.propConfig = this.property.propConfig;
    this.offerCost = this.property.offerCost;
    this.areaSqft = this.property.areaSqft;
    this.address = this.property.address;
    this.street = this.property.street;
    this.city = this.property.city;
  }

  editProperty(){
    let propIdFront = sessionStorage.getItem("propId");
    console.log(propIdFront);
    let property = {
      propId: propIdFront,
      propConfig: this.propConfig,
      offerType: this.offerType,
      offerCost: this.offerCost,
      areaSqft: this.areaSqft,
      address: this.address,
      street: this.street,
      city: this.city
    }
    this.propertyService.editProperty(property).subscribe(
      (response) => {
        sessionStorage.removeItem("propId");
        this.router.navigate(['/myproperties']);
      }
    );
  }

}
