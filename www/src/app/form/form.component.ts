import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  checkoutForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.checkoutForm = this.formBuilder.group({
      name: '',
      email: '',
      number: 1,
      type: '',
      toogle: false,
      text: '',
      color: '',
      date: '',
      datetimeLocal: '',
      file: '',
      month: '',
      password: '',
      radio: '',
      range: '',
      search: '',
      tel: '',
      time: '',
      url: '',
      week: '',
    });
  }

  ngOnInit(): void {
  }

  /*
  c<olor: "#197162"
date: "2020-12-05"
datetimeLocal: "2020-12-18T23:25"
email: "email"
file: "C:\fakepath\Faktura956.pdf"
month: "2020-03"
name: "name"
number: 4
password: "password"
radio: undefined
range: 60
search: "search"
tel: "070"
text: ""
time: "20:24"
toogle: true
type: "Type 1"
url: "https://www.scaningredients.com"
week: "2020-W45"
   */
  onSubmit(customerData: any): void {
    console.log('Submit' + customerData);
    console.dir(customerData);
  }
}
