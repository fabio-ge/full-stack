import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { authGuard } from './auth.guard';
import { CreaComponent } from './crea/crea.component';
import { GuardaComponent } from './guarda/guarda.component';
import { ElencoComponent } from './elenco/elenco.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent, canActivate: [authGuard]},
  { path: 'crea', component: CreaComponent, canActivate: [authGuard]},
  { path: 'guarda', component: GuardaComponent, canActivate: [authGuard]},
  { path: 'elenco/:bundleId', component: ElencoComponent, canActivate: [authGuard]},
  { path: 'login', component: LoginComponent},
  { path: '' , redirectTo: 'home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
