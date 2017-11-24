import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';

// import { AppRoutingModule } from "./app-routing.module";
import { ShoppingListModule } from './shopping-list/shopping-list.module';

import { AppComponent } from './app.component';

import { ErrorPageComponent } from './error-page/error-page.component';

import { AuthModule } from './auth/auth.module';
import {CoreModule} from './core/core.module';
import {AppRoutingModule} from './app-routing.module';


@NgModule({
  declarations: [
    AppComponent,
    ErrorPageComponent,
  ],
  imports: [
    BrowserModule,
    HttpModule,
    AuthModule,
    AppRoutingModule,
    ShoppingListModule,
    CoreModule
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule {
}
