import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DealService } from 'src/app/service/deal.service';
import { PropertyService } from 'src/app/service/property.service';

@Component({
  selector: 'app-criteria-list',
  templateUrl: './criteria-list.component.html',
  styleUrls: ['./criteria-list.component.css']
})
export class CriteriaListComponent implements OnInit {

  properties: any;
  user: any;

  constructor(private propService: PropertyService,private router: Router,private dealService: DealService) { }

  ngOnInit(): void {
    this.properties = JSON.parse(sessionStorage.getItem("criteriaList")!);
    this.user = JSON.parse(sessionStorage.getItem("user")!);
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
          this.router.navigate(['/deals'])
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
