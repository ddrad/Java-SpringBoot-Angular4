import {Injectable} from '@angular/core';
import {Http, Response, Headers} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import {RegisterData} from './register-data.model';
import {TokenData} from './token-data.model';

@Injectable()
export class AuthService {

  constructor(private http: Http) {
  }

  isLogIn = false;
  tokenData: TokenData = null;

  isAuthenticared() {
    if (this.tokenData) {
      this.isLogIn = true;
    }
    else if (localStorage.getItem('crafterTokenData')) {
      this.tokenData = JSON.parse(localStorage.getItem('crafterTokenData'));
      this.isLogIn = true;
    }
    else {
      this.isLogIn = false;
    }
    return this.isLogIn;
  }

  isAuthenticaredAsMaster() {
    if (this.isAuthenticared() && this.tokenData.customerType === 'MASTER') {
      return true;
    }
  }

  getTokenAlias() {
    if ( this.tokenData ) {
      return this.tokenData.token;
    }

    if ( this.isAuthenticared()) {
      return this.tokenData.token;
    }
  }

  signIn(email: string, password: string) {
    this.http.post('http://localhost:4200/app-auth/sign-in', {email, password})
      .catch(
        (error: Response) => {
          return Observable.throw('error in Sign-In() ' + error);
        }
      )
      .subscribe(
        (response: Response) => {
          this.tokenData = response.json();
          if (this.tokenData.status === 'ACTIVE') {
            localStorage.setItem('crafterTokenData', JSON.stringify(this.tokenData));
            this.isLogIn = true;
          }
        }
      );
  }

  signUp(registerData: RegisterData) {
    const headers = new Headers({'Content-Type': 'application/json'});

    this.http.post('http://localhost:4200/app-auth/sign-up', registerData)
      .catch(
        (error: Response) => {
          return Observable.throw('error in Sign-In() ' + error);
        }
      ).subscribe(
      (response: Response) => {
        this.tokenData = response.json();
        if (this.tokenData.status === 'ACTIVE') {
          localStorage.setItem('crafterTokenData', JSON.stringify(this.tokenData));
          this.isLogIn = true;
        }
      }
    );
  }

  signOut() {
    this.tokenData = null;
    this.isLogIn = false;
    localStorage.removeItem('crafterTokenData');
  }
}
