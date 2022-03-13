import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BrokerService {

  constructor(private httpClient: HttpClient) { }

  addBroker(user: any){
    return this.httpClient.post("http://localhost:8080/broker/add", user);
  }

  editBroker(user: any){
    return this.httpClient.post("http://localhost:8080/broker/edit",user);
  }

  deleteBroker(userId: any){
    return this.httpClient.post("http://localhost:8080/broker/delete",userId);
  }

  findBroker(userId: any){
    return this.httpClient.post("http://localhost:8080/broker/find", userId);
  }
}
