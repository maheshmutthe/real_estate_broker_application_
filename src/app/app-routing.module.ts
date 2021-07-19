import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DealComponent } from './deals/deal/deal.component';
import { HeaderComponent } from './header/header/header.component';
import { LandingComponent } from './landing/landing/landing.component';
import { LoginComponent } from './login/login/login.component';
import { EditProfileComponent } from './profile/edit-profile/edit-profile.component';
import { ProfileComponent } from './profile/profile/profile.component';
import { AddPropertyComponent } from './property/add-property/add-property.component';
import { CriteriaFormComponent } from './property/criteria-form/criteria-form.component';
import { CriteriaListComponent } from './property/criteria-list/criteria-list.component';
import { EditPropertyComponent } from './property/edit-property/edit-property.component';
import { ListAllPropertiesComponent } from './property/list-all-properties/list-all-properties.component';
import { MyPropertiesComponent } from './property/my-properties/my-properties.component';
import { RegisterComponent } from './register/register/register.component';

const routes: Routes = [
  {
    path: "",
    component: LandingComponent
  },
  {
    path: "landing",
    component: LandingComponent
  },
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: "register",
    component: RegisterComponent
  },
  {
    path: "header",
    component: HeaderComponent
  },
  {
    path: "properties",
    component: ListAllPropertiesComponent
  },
  {
    path: "profile",
    component: ProfileComponent
  },
  {
    path: "edit",
    component: EditProfileComponent
  },
  {
    path: "myproperties",
    component: MyPropertiesComponent
  },
  {
    path: "deals",
    component: DealComponent
  },
  {
    path: "addProp",
    component: AddPropertyComponent
  },
  {
    path: "editProp",
    component: EditPropertyComponent
  },
  {
    path: "criteria",
    component: CriteriaFormComponent
  },
  {
    path: "criterialist",
    component: CriteriaListComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
