import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { AuthService } from '@auth0/auth0-angular';

@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styleUrls: ['./search-page.component.css']
})
export class SearchPageComponent implements OnInit {

  input = '';

  host: any;
  rows: [];

  constructor(private http: HttpClient, private auth: AuthService) {
    this.host = environment.host;
  }

  ngOnInit(): void {
  }

  doSearch(): void {
    const data = {
      query: this.input
    };
    const options = {
      headers: {
      }
    };
    this.rows = [];
    this.http.post<any>(this.host + '/api/v1/public/search/', data, options).subscribe(result => {
      console.log(result);
      this.rows = result.products;
    });
  }
}
