import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { AuthService } from '@auth0/auth0-angular';
import { AnalyticsService } from '../services/analytics.service';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit {

  host;
  checkoutForm: FormGroup;
  categories = [];
  public imageUrl: string;

  constructor(private formBuilder: FormBuilder, private http: HttpClient, private auth: AuthService, analytics: AnalyticsService) {
    analytics.behaviour('product-form-page');
    this.host = environment.host;

    this.checkoutForm = this.formBuilder.group({
      name: '',
      description: '',
      cost: null,
      category: null
    });
    this.http.get<any>(this.host + '/api/v1/public/product-categories').subscribe(response => {
      console.log(response);
      this.categories = response;
    })
      ;
  }

  ngOnInit(): void {
  }

  onSubmit(customerData: any): void {
    console.dir(customerData);
    customerData.imageUrl = this.imageUrl;
    this.auth.getAccessTokenSilently().subscribe(accessToken => {
      const options = {
        headers: {
          Authorization: 'Bearer ' + accessToken
        }
      };
      this.http.post<number>(this.host + '/api/v1/products', customerData, options).subscribe(response => {
        console.log(response);
      });
    });
  }

  onFileChange($event): void {
    this.auth.getAccessTokenSilently().subscribe(accessToken => {
      const file = $event.srcElement.files[0];
      const url = environment.host + '/api/v1/image/';
      const options = {
        headers: {
          Authorization: 'Bearer ' + accessToken
        }
      };
      this.http.get<any>(url, options).subscribe(resource => {
        console.dir(resource);
        var data = new FormData();
        data.append('myFile', file);
        this.http.post<any>(resource.imageUploadUrl, data, options).subscribe(response => {
          console.log(response.imageServeUrl);
          this.imageUrl = response.imageServeUrl;
        })
      });
    });
  }
}
