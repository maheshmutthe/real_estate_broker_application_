import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  user: any;

  constructor(private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    //console.log(this.activatedRoute.snapshot.paramMap.getAll('user'));
    console.log(JSON.parse(sessionStorage.getItem("user")!))
    this.user = JSON.parse(sessionStorage.getItem("user")!);
  }

}
