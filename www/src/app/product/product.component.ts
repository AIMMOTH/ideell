import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { ApiService } from '../api.service';

export interface ProductI {
  customer: string;
  department: string;
  productId: number;
  name: string;
  cost: number;
  description: string;
  category: string;
  imageUrl: string;
}

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  product: ProductI;
  customer: string;
  department: string;
  productId: number;

  constructor(private route: ActivatedRoute, private api: ApiService) {
    this.route.queryParams.subscribe(params => {
      this.customer = params.customer;
      this.department = params.department;
      this.productId = params.productId;

      api.getPublic<ProductI>('/api/v1/products/' + this.customer + '/' + this.department + '/' + this.productId).subscribe(product => {
        console.dir(product);
        this.product = product;
      });
    });
  }

  ngOnInit(): void {
  }
  b64toBlob = (b64Data, contentType = '', sliceSize = 512) => {
    const byteCharacters = atob(b64Data);
    const byteArrays = [];

    for (let offset = 0; offset < byteCharacters.length; offset += sliceSize) {
      const slice = byteCharacters.slice(offset, offset + sliceSize);

      const byteNumbers = new Array(slice.length);
      for (let i = 0; i < slice.length; i++) {
        byteNumbers[i] = slice.charCodeAt(i);
      }

      const byteArray = new Uint8Array(byteNumbers);
      byteArrays.push(byteArray);
    }

    const blob = new Blob(byteArrays, { type: contentType });
    return blob;
  }

  downloadExcel(): void {
    const path = '/api/v1/excel/product/' + this.customer + '/' + this.department + '/' + this.productId;
    this.api.getPublicAsText(path).subscribe(response => {
      const file = this.b64toBlob(response, 'application/octet-stream');

      const fileURL = window.URL.createObjectURL(file);
      const a = document.createElement('a');

      a.href = fileURL;
      a.target = '_blank';
      a.download = this.product.name + ".xls";
      document.body.appendChild(a);
      a.click();
    });
  }

  downloadPdf() {
    const path = '/api/v1/pdf/product/' + this.customer + '/' + this.department + '/' + this.productId;
    this.api.getPublicAsText(path).subscribe(response => {
      const file = this.b64toBlob(response, 'application/octet-stream');

      const fileURL = window.URL.createObjectURL(file);
      const a = document.createElement('a');

      a.href = fileURL;
      a.target = '_blank';
      a.download = this.product.name + ".pdf";
      document.body.appendChild(a);
      a.click();
    });

  }
}
