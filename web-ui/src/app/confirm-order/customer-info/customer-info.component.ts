import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-customer-info',
  templateUrl: './customer-info.component.html',
  styleUrls: ['./customer-info.component.css']
})
export class CustomerInfoComponent implements OnInit {

  constructor() { }

  
  ngOnInit(): void {
  }

  onOrder(form: NgForm) {
    const email = form.value.email;
    const firstName = form.value.firstName;
    const phoneNumber = form.value.phoneNumber;
  }

}
