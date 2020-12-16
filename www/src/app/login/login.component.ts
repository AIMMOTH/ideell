import { Component, OnInit } from '@angular/core';
import { AuthService } from '@auth0/auth0-angular';
import { AnalyticsService } from '../services/analytics.service';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(public auth: AuthService, analytics: AnalyticsService) {
    analytics.behaviour('login-page');
  }

  ngOnInit(): void {
  }

}
