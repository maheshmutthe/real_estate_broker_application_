import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DealService {

  constructor(private httpClient: HttpClient) { }

  addDeal(dealDetail: any){
    return this.httpClient.post("http://localhost:8080/deal/saveDeal",dealDetail);
  }
}
