import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private httpClient: HttpClient) { }

  addCustomer(user: any){
    return this.httpClient.post("http://localhost:8080/customer/add", user);
  }

  editCustomer(user: any){
    return this.httpClient.post("http://localhost:8080/customer/edit", user);
  }

  deleteCustomer(userId: any){
    return this.httpClient.post("http://localhost:8080/customer/delete", userId);
  }

  findCustomer(userId: any){
    return this.httpClient.post("http://localhost:8080/customer/find", userId);
  }
}
