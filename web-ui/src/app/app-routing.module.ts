import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {GreetingComponent} from './core/greeting/greeting.component';
import {ShoppingListComponent} from './shopping-list/shopping-list.component';
import {ErrorPageComponent} from './error-page/error-page.component';

const appRouts: Routes = [
  {path: '', component: GreetingComponent, pathMatch: 'full'},
  {path: 'ads', loadChildren: './ads-catalog/ads-catalog.module#AdsCatalogModule'},
  {path: 'ads-own', loadChildren: './ads-own/ads-own.module#AdsOwnModule'},
  {path: 'shopping-list', component: ShoppingListComponent},
  {path: 'page-not-found', component: ErrorPageComponent, data: {message: 'Page not found!'}},
  {path: '**', redirectTo: '/page-not-found'}
];

@NgModule({
  imports : [
    RouterModule.forRoot(appRouts)
  ],
  exports : [
    RouterModule
  ]
})
export class AppRoutingModule {}
