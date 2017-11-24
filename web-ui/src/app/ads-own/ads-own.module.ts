import {NgModule} from '@angular/core';

import {AdsModule} from '../ads/ads.module';
import {AdsOwnComponent} from './ads-own.component';
import {AdsOwnRoutingModule} from './ads-own-routing.module';
import {AdOwnListComponent} from './ad-own-list/ad-own-list.component';
import {AdService} from "../ads/ad.service";


@NgModule({
  declarations: [
    AdsOwnComponent,
    AdOwnListComponent
  ],
  imports: [
    AdsModule,
    AdsOwnRoutingModule
  ],
  providers: [
    AdService
  ],
  exports: []
})

export class AdsOwnModule {
}
