import { Component, OnInit, Inject  } from '@angular/core';
import { AuthService } from '@auth0/auth0-angular';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'app-auth-button',
  templateUrl: './auth-button.component.html',
  styleUrls: ['./auth-button.component.css']
})
export class AuthButtonComponent implements OnInit {

  constructor(@Inject(DOCUMENT) public document: Document, public auth: AuthService) {
  }

  ngOnInit(): void {
  }

  redirect() {
    const o = { redirect_uri: 'http://localhost:4200/#/' };
    this.auth.loginWithRedirect();
  }
}
