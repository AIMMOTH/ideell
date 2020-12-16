import { Component, OnInit } from '@angular/core';
import { AuthService } from '@auth0/auth0-angular';
import { AnalyticsService } from '../services/analytics.service';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-start-page',
  templateUrl: './start-page.component.html',
  styleUrls: ['./start-page.component.css']
})
export class StartPageComponent implements OnInit {

  constructor(translate: TranslateService, analytics: AnalyticsService) {
    analytics.behaviour('start-page');
    translate.addLangs(['en', 'sv']);
    translate.setDefaultLang('sv');
    translate.use('sv');
  }

  ngOnInit(): void {

  }

}
