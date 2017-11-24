import {NgModule} from '@angular/core';
import {AppCommonModule} from '../common/app-common.module';
import {SignInComponent} from './sign-in/sign-in.component';
import {SignUpComponent} from './sign-up/sign-up.component';
import {AuthRoutingModule} from './auth-routing.module';

@NgModule({
  declarations: [
    SignInComponent,
    SignUpComponent
  ],
  imports: [
    AppCommonModule,
    AuthRoutingModule
  ]
})
export class AuthModule {
}
