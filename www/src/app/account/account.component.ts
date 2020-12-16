import { Component, OnInit } from '@angular/core';
import { concatMap, tap, pluck } from 'rxjs/operators';
import { AuthService } from '@auth0/auth0-angular';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  metadata = {};
  user;

  constructor(public auth: AuthService, private http: HttpClient) {
    this.auth.user$
      .pipe(
        concatMap((user) =>
          // Use HttpClient to make the call
          this.http.get(
            encodeURI(`https://stockholm-framework.eu.auth0.com/api/v2/users/${user.sub}`)
          )
        )
      )
      .subscribe(value => {
        console.dir(value);
      });
  }

  ngOnInit(): void {
  }

}
