import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http'
import { ReactiveFormsModule } from '@angular/forms'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LandingComponent } from './landing/landing/landing.component';
import { LoginComponent } from './login/login/login.component';
import { BasicAuthInterceptorService } from './service/basic-auth-interceptor.service';
import { HeaderComponent } from './header/header/header.component';
import { RegisterComponent } from './register/register/register.component';
import { ListAllPropertiesComponent } from './property/list-all-properties/list-all-properties.component';
import { ProfileComponent } from './profile/profile/profile.component';
import { EditProfileComponent } from './profile/edit-profile/edit-profile.component';
import { MyPropertiesComponent } from './property/my-properties/my-properties.component';
import { DealComponent } from './deals/deal/deal.component';
import { AddPropertyComponent } from './property/add-property/add-property.component';
import { EditPropertyComponent } from './property/edit-property/edit-property.component';
import { CriteriaFormComponent } from './property/criteria-form/criteria-form.component';
import { CriteriaListComponent } from './property/criteria-list/criteria-list.component';

@NgModule({
  declarations: [
    AppComponent,
    LandingComponent,
    LoginComponent,
    HeaderComponent,
    RegisterComponent,
    ListAllPropertiesComponent,
    ProfileComponent,
    EditProfileComponent,
    MyPropertiesComponent,
    DealComponent,
    AddPropertyComponent,
    EditPropertyComponent,
    CriteriaFormComponent,
    CriteriaListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptorService, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
