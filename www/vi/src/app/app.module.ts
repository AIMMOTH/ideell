import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { APP_BASE_HREF, CommonModule } from '@angular/common';
import { ServiceWorkerModule } from '@angular/service-worker';
import { environment } from '../environments/environment';
import { TranslateModule } from '@ngx-translate/core';
import { UserProfileComponent } from './auth0/user-profile/user-profile.component';
import { FooterComponent } from './common/footer/footer.component';
import { StartComponent } from './pages/start/start.component';
import { AuthButtonComponent } from './auth0/auth-button/auth-button.component';

// Fix Auth0
import { AuthModule } from '@auth0/auth0-angular';
import { HttpClientModule } from '@angular/common/http';
import { AuthHttpInterceptor } from '@auth0/auth0-angular';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    UserProfileComponent,
    AuthButtonComponent,
    FooterComponent,
    StartComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    CommonModule,
    // PWA
    ServiceWorkerModule.register('ngsw-worker.js', {
      enabled: environment.production,
      // Register the ServiceWorker as soon as the app is stable
      // or after 30 seconds (whichever comes first).
      registrationStrategy: 'registerWhenStable:30000'
    }),
    TranslateModule.forRoot({
      defaultLanguage: 'sv'
    }),
    AuthModule.forRoot({
      domain: 'ideell.eu.auth0.com',
      clientId: 'uQFq1fpdbJzCZMUFz61E0Ifbjb1VFBa8',

      // Request this audience at user authentication time
      audience: 'https://ideell.eu.auth0.com/api/v2/',

      // Request this scope at user authentication time
      scope: 'read:current_user',

      // Specify configuration for the interceptor
      httpInterceptor: {
        allowedList: [
          {
            // Match any request that starts 'https://ideell.eu.auth0.com/api/v2/' (note the asterisk)
            uri: 'https://ideell.eu.auth0.com/api/v2/*',
            tokenOptions: {
              // The attached token should target this audience
              audience: 'https://ideell.eu.auth0.com/api/v2/',

              // The attached token should have these scopes
              scope: 'read:current_user'
            }
          }
        ]
      }
    }),
    // Fix Auth0
    HttpClientModule
  ],
  providers: [
    // {provide: APP_BASE_HREF, useValue: '/'},
    // { provide: HTTP_INTERCEPTORS, useClass: AuthHttpInterceptor, multi: true }, // Auth0 requirements
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
