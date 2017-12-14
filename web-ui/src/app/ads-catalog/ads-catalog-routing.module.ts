import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AdsCatalogComponent} from './ads-catalog.component';
import {AdStartComponent} from '../ads/ad-start/ad-start.component';
import {AdEditComponent} from '../ads/ad-edit/ad-edit.component';
import {AdDetailsComponent} from '../ads/ad-details/ad-details.component';

const adsCatalogRouts: Routes = [
  {
    path: '', component: AdsCatalogComponent, children: [
    {path: '', component: AdStartComponent},
    {path: 'new', component: AdEditComponent},
    {path: ':id/:index', component: AdDetailsComponent},
  ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(adsCatalogRouts)
  ],
  exports: [
    RouterModule
  ]
})

export class AdsCatalogRoutingModule {
}
