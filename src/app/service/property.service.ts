import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PropertyService {

  constructor(private httpClient: HttpClient) { }

  addProperty(userId: any, property: any){
    return this.httpClient.post("http://localhost:8080/property/add/"+userId,property);
  }

  editProperty(property: any){
    return this.httpClient.post("http://localhost:8080/property/edit",property);
  }

  deleteProperty(propertyId: any){
    return this.httpClient.post("http://localhost:8080/property/remove",propertyId);
  }

  findAllProperties() {
    // let token = sessionStorage.getItem("token");

    // let httpOption = {
    //   headers: new HttpHeaders({
    //     'content-type': 'application/json',
    //     'Authorization': token!
    //   })
    // };
    return this.httpClient.get("http://localhost:8080/property/findAllUnsold");
  }

  findByCriteria(propertyCriteria: any){
    return this.httpClient.post("http://localhost:8080/property/findByCriteria",propertyCriteria);
  }
}
