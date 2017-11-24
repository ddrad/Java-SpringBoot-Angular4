import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {Location} from "@angular/common";
import {AuthService} from "../auth.service";

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  constructor(private authService: AuthService, private location: Location) { }

  defaulCustomerType = 'client';

  ngOnInit() {
  }

  onSignUp(sigUpForm: NgForm) {
    this.authService.signUp(sigUpForm.value);
    this.location.back();
  }

}
