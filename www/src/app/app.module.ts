import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StartPageComponent } from './start-page/start-page.component';
import { SearchPageComponent } from './search-page/search-page.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { LoginComponent } from './login/login.component';
import { AccountComponent } from './account/account.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

// Auth0
import { AuthModule } from '@auth0/auth0-angular';
import { AuthHttpInterceptor } from '@auth0/auth0-angular';

// Forms
import { HttpClientModule, HttpClient, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

// ngx translate
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';

// Material
import { MatTreeModule } from '@angular/material/tree';
// import {MatCheckboxModule} from '@angular/material/checkbox';
// import { MatCheckboxModule } from '@angular/material';
// import { MatButtonModule } from '@angular/material';
import { MatRippleModule, MatNativeDateModule } from '@angular/material/core';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatInputModule } from '@angular/material/input';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatSliderModule } from '@angular/material/slider';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatMenuModule } from '@angular/material/menu';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatListModule } from '@angular/material/list';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { MatStepperModule } from '@angular/material/stepper';
import { MatTabsModule } from '@angular/material/tabs';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatChipsModule } from '@angular/material/chips';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatDialogModule } from '@angular/material/dialog';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTableModule } from '@angular/material/table';
import { MatSortModule } from '@angular/material/sort';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatDividerModule } from '@angular/material/divider';
import { LogoutComponent } from './logout/logout.component';
import { FormComponent } from './form/form.component';
import { MaterialKitchenSinkComponent } from './material-kitchen-sink/material-kitchen-sink.component';
import { ProductFormComponent } from './product-form/product-form.component';
import { SheetsComponent } from './sheets/sheets.component';
import { ProductComponent } from './product/product.component';
import { ChatComponent } from './chat/chat.component';

export function createTranslateLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}
@NgModule({
  declarations: [
    AppComponent,
    StartPageComponent,
    SearchPageComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    AccountComponent,
    LogoutComponent,
    FormComponent,
    MaterialKitchenSinkComponent,
    ProductFormComponent,
    SheetsComponent,
    ProductComponent,
    ChatComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,

    // Auth0
    AuthModule.forRoot({
      // Connect to Client Application
      domain: 'stockholm-framework.eu.auth0.com',
      clientId: 'jYbU3erolP51lUE5lOjOqbP3Nu47sJR1',

      // Request this audience at user authentication time
      audience: 'https://stockholm-framework.eu.auth0.com/api/v2/',

      // Request this scope at user authentication time
      scope: 'read:current_user',

      // Specify configuration for the interceptor
      httpInterceptor: {
        allowedList: [
          {
            // Match any request that starts 'https://stockholm-framework.eu.auth0.com/api/v2/' (note the asterisk)
            uri: 'https://stockholm-framework.eu.auth0.com/api/v2/*',
            tokenOptions: {
              // The attached token should target this audience
              audience: 'https://stockholm-framework.eu.auth0.com/api/v2/',

              // The attached token should have these scopes
              scope: 'read:current_user'
            }
          }
        ]
      }
    }),

    // Forms
    ReactiveFormsModule, // ng model and form group
    FormsModule, // ng model and form group
    HttpClientModule,

    // ngx translate
    BrowserModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: (createTranslateLoader),
        deps: [HttpClient]
      }
    }),

    // Material design
    MatDividerModule,
    MatSelectModule,
    MatInputModule,
    MatSliderModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatDatepickerModule, MatNativeDateModule, // for date picker
    MatDividerModule,
    MatListModule,
    MatExpansionModule,
    MatGridListModule,
    MatIconModule,
    MatMenuModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatRippleModule,
    MatSidenavModule,
    MatStepperModule,
    MatTreeModule
  ],
  exports: [
    MatFormFieldModule,
    MatInputModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthHttpInterceptor, multi: true }, // Auth0 requirements
    MatDatepickerModule // for date picker
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

// required for AOT compilation
export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}