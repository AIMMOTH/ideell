import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '@auth0/auth0-angular';
import { AnalyticsService } from '../services/analytics.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-sheets',
  templateUrl: './sheets.component.html',
  styleUrls: ['./sheets.component.css']
})
export class SheetsComponent implements OnInit {

  constructor(private http: HttpClient, private auth: AuthService, analytics: AnalyticsService) {
  }

  ngOnInit(): void {
  }

  load(): void {
    this.auth.getAccessTokenSilently().subscribe(token => {
      const options = {
        headers: {
          Authorization: 'Bearer ' + token
        }
      };
      this.http.get<any>(environment.host + '/api/v1/sheets/', options).subscribe(results => {
        console.dir(results);
      });
    });
  }
}
