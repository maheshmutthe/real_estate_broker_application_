import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PropertyService } from 'src/app/service/property.service';

@Component({
  selector: 'app-criteria-form',
  templateUrl: './criteria-form.component.html',
  styleUrls: ['./criteria-form.component.css']
})
export class CriteriaFormComponent implements OnInit {

  config: any = null;
  offer: any = null;
  city: any = null;
  minCost: any = null;
  maxCost: any = null;

  constructor(private propertyService: PropertyService,private router: Router) { }

  ngOnInit(): void {
  }

  searchCriteria(){
    let propCriteria = {
      config: this.config,
      offer: this.offer,
      city: this.city,
      minCost: this.minCost,
      maxCost: this.maxCost
    }
    
    let properties: any;
    this.propertyService.findByCriteria(propCriteria).subscribe(
      (response: any) => {
        console.log(response);
        if(!response.length){
          alert("No property found matching given criteria");
        } else {
          if(sessionStorage.getItem("criteriaList")){
            sessionStorage.removeItem("criteriaList");
          }
          sessionStorage.setItem("criteriaList",JSON.stringify(response));
          this.router.navigate(['/criterialist']);
        }
      },
      (errResponse) => {
        alert("No property found matching given criteria");
      }
    );
    this.config = null;
    this.offer = null;
    this.city = null;
    this.minCost = null;
    this.maxCost = null;
  }

}
