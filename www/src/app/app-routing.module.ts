import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StartPageComponent } from './start-page/start-page.component';
import { SearchPageComponent } from './search-page/search-page.component';
import { LoginComponent } from './login/login.component';
import { AccountComponent } from './account/account.component';
import { LogoutComponent } from './logout/logout.component';
import { FormComponent } from './form/form.component';
import { ProductFormComponent } from './product-form/product-form.component';
import { MaterialKitchenSinkComponent } from './material-kitchen-sink/material-kitchen-sink.component';
import { SheetsComponent } from './sheets/sheets.component';
import { ProductComponent } from './product/product.component';

const routes: Routes = [
  { path: '', component: StartPageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'account', component: AccountComponent },
  { path: 'form', component: FormComponent },
  { path: 'logout', component: LogoutComponent },
  { path: 'search', component: SearchPageComponent },
  { path: 'sheets', component: SheetsComponent },
  { path: 'material', component: MaterialKitchenSinkComponent },
  { path: 'product', component: ProductComponent },
  { path: 'product-form', component: ProductFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: false })],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
