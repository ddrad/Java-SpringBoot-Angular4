import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import { SignInComponent } from "./sign-in/sign-in.component";
import { SignUpComponent } from "./sign-up/sign-up.component";


const authRouts: Routes = [
    {path: 'sign-in', component: SignInComponent},
    {path: 'sign-up', component: SignUpComponent},
];

@NgModule({
  imports : [
    RouterModule.forChild(authRouts)
  ],
  exports : [
    RouterModule
  ]
})
export class AuthRoutingModule {}
