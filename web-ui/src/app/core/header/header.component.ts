import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../../auth/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  @Output() featureSelectedEvent = new EventEmitter<string>();

  constructor(private router: Router, private authService: AuthService) {
  }

  ngOnInit() {
  }

  isAuthenticared() {
    return this.authService.isAuthenticared();
  }

  isAuthenticaredAsMaster() {
    console.log('it is isAuthAsMaster in the header');
    return this.authService.isAuthenticaredAsMaster();
  }

  onSignOut() {
    return this.authService.signOut();
  }

}
