import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AdsOwnComponent} from './ads-own.component';
import {AdStartComponent} from '../ads/ad-start/ad-start.component';
import {AdEditComponent} from '../ads/ad-edit/ad-edit.component';
import {AdDetailsComponent} from '../ads/ad-details/ad-details.component';

const adsOwnRouts: Routes = [
  {
    path: '', component: AdsOwnComponent, children: [
    {path: '', component: AdStartComponent},
    {path: 'new', component: AdEditComponent},
    {path: ':id/:index', component: AdDetailsComponent},
    {path: ':id/:index/edit', component: AdEditComponent},
  ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(adsOwnRouts)
  ],
  exports: [
    RouterModule
  ]
})
export class AdsOwnRoutingModule {
}
