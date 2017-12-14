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

  isAuthenticated() {
    return this.authService.isAuthenticated();
  }

  isAuthenticatedAsMaster() {
    return this.authService.isAuthenticatedAsMaster();
  }

  onSignOut() {
    return this.authService.signOut();
  }

}
