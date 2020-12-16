import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { AuthService } from '@auth0/auth0-angular';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient, private auth0: AuthService) { }

  getPublic<T>(route: string, params?: any): Observable<T> {
    const options = {
      params
    };
    return this.http.get<T>(environment.host + route, options);
  }

  getPublicAsText(route: string, params?: any) {
    const options = {
      responseType: 'text' as 'json',
      params
    };
    return this.http.get(environment.host + route, options);
  }

  get<T>(route: string, callback: (T) => void, params?: any) {
    return this.auth0.getAccessTokenSilently().subscribe(token => {
      const options = {
        params,
        headers: {
          Authorization: 'Bearer ' + token
        }
      };
      callback(this.http.get<T>(environment.host + route, options));
    });
  }

  postPublic<T>(route: string, body: any): Observable<T> {
    const options = {
    };
    return this.http.post<T>(environment.host + route, body, options);
  }
}
