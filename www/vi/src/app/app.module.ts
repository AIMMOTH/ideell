import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { APP_BASE_HREF, CommonModule } from '@angular/common';
import { ServiceWorkerModule } from '@angular/service-worker';
import { environment } from '../environments/environment';
import { TranslateModule } from '@ngx-translate/core';

import { AuthModule } from '@auth0/auth0-angular';
import { UserProfileComponent } from './auth0/user-profile/user-profile.component';
import { FooterComponent } from './common/footer/footer.component';
import { StartComponent } from './pages/start/start.component';
import { AuthButtonComponent } from './auth0/auth-button/auth-button.component';

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
      clientId: 'uQFq1fpdbJzCZMUFz61E0Ifbjb1VFBa8'
    })
  ],
  providers: [
    {provide: APP_BASE_HREF, useValue: '/vi/'}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
