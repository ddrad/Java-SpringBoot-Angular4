import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import {RegisterData} from './register-data.model';
import {TokenData} from './token-data.model';

@Injectable()
export class AuthService {

  constructor(private httpClient: HttpClient) {
  }

  isLogIn = false;
  tokenData: TokenData = null;

  isAuthenticated() {
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

  isAuthenticatedAsMaster() {
    if (this.isAuthenticated() && this.tokenData.customerType === 'MASTER') {
      return true;
    }
  }

  getTokenAlias() {
    if ( this.tokenData ) {
      return this.tokenData.token;
    }

    if ( this.isAuthenticated()) {
      return this.tokenData.token;
    }
  }

  signIn(email: string, password: string) {
    this.httpClient.post<TokenData>('http://localhost:4200/app-auth/sign-in', {email, password})
      .catch(
        (error: Response) => {
          return Observable.throw('error in Sign-In() ' + error);
        }
      )
      .subscribe(
        (tokenData) => {
          this.tokenData = tokenData;
          if (this.tokenData.status === 'ACTIVE') {
            localStorage.setItem('crafterTokenData', JSON.stringify(this.tokenData));
            this.isLogIn = true;
          }
        }
      );
  }

  signUp(registerData: RegisterData) {
    const headers = new Headers({'Content-Type': 'application/json'});

    this.httpClient.post<TokenData>('http://localhost:4200/app-auth/sign-up', registerData)
      .catch(
        (error: Response) => {
          return Observable.throw('error in Sign-In() ' + error);
        }
      ).subscribe(
      (tokenData) => {
        this.tokenData = tokenData;
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
