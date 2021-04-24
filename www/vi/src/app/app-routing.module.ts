import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthButtonComponent } from './auth0/auth-button/auth-button.component';
import { UserProfileComponent } from './auth0/user-profile/user-profile.component';
import { StartComponent } from './pages/start/start.component';

const routes: Routes = [
  { path: '', component: StartComponent },
  { path: 'login', component: AuthButtonComponent },
  { path: 'profile', component: UserProfileComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
