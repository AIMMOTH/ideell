import { Component, OnInit } from '@angular/core';
import { AuthService } from '@auth0/auth0-angular';
import { ApiService } from 'src/app/service/api.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  constructor(public auth: AuthService, private api: ApiService) {
    this.auth.user$.subscribe(value => {
      console.log('profile:');
      console.dir(value);
    });
  }

  ngOnInit(): void {
  }

  getPublic() {
    this.api.getPublic('/api/v1/public/').subscribe(result => {
      console.dir(result);
    })
    ;
  }

  getPrivate() {
    this.api.get('/api/v1/private/', result => result.subscribe(value => {
      console.dir(value);
    }));
  }
}
