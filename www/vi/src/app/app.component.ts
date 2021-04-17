import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  param = {
    name: 'Carl'
  };

  constructor(translate: TranslateService) {
    translate.setTranslation('en', {
      slogan: 'Share your day, engage in events and groups.',
      welcomeUser: 'Welcome {{ name }}!'
    });
    translate.setTranslation('sv', {
      slogan: 'Dela din dag, gå med i events och grupper.',
      welcomeUser: 'Välkommen {{ name }}!'
    });
    translate.setDefaultLang('sv');
    translate.use('sv');
  }
}
