import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LandingComponent} from './landing/landing.component';
import {LoginComponent} from './login/login.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {MatListModule} from "@angular/material/list";
import {DemandeComponent} from './demande/demande.component';
import {MatIconModule} from '@angular/material/icon';
import {MatTableModule} from "@angular/material/table";
import {NgxMatFileInputModule} from "@angular-material-components/file-input";
import {MatRadioModule} from "@angular/material/radio";
import {MarchandiseComponent} from './marchandise/marchandise.component';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatDialogModule} from "@angular/material/dialog";
import {HttpClientModule} from "@angular/common/http";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import { SuivieComponent } from './suivie/suivie.component';
import {MatSnackBarModule} from "@angular/material/snack-bar";
import { InscriptionComponent } from './inscription/inscription.component';
import {MatCardModule} from "@angular/material/card";
import { DashboardComponent } from './dashboard/dashboard.component';
import {MatMenuModule} from "@angular/material/menu";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSortModule} from "@angular/material/sort";
import { InfoPayeurComponent } from './info-payeur/info-payeur.component';



@NgModule({
  declarations: [
    AppComponent,
    LandingComponent,
    LoginComponent,
    DemandeComponent,
    MarchandiseComponent,
    SuivieComponent,
    InscriptionComponent,
    DashboardComponent,
    InfoPayeurComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatButtonModule,
    MatListModule,
    MatIconModule,
    MatTableModule,
    NgxMatFileInputModule,
    MatRadioModule,
    FormsModule,
    MatToolbarModule,
    MatDialogModule,
    HttpClientModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSnackBarModule,
    MatCardModule,
    MatMenuModule,
    MatPaginatorModule,
    MatSortModule
  ],
  providers: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
