import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LandingComponent} from './landing/landing.component';
import {LoginComponent} from "./login/login.component";
import {DemandeComponent} from "./demande/demande.component";
import {MarchandiseComponent} from "./marchandise/marchandise.component";
import {InscriptionComponent} from "./inscription/inscription.component";
import {DashboardComponent} from "./dashboard/dashboard.component";

const routes: Routes = [
  {path: '', component: LandingComponent},
  {path: 'login', component: LoginComponent},
  {path: 'demande', component: DemandeComponent},
  {path: 'marchandise', component: MarchandiseComponent},
  {path: 'register', component: InscriptionComponent},
  {path: 'dashboard', component: DashboardComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
