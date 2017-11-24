import {NgModule} from '@angular/core';

import {AdsModule} from '../ads/ads.module';
import {AdService} from '../ads/ad.service';
import {AdsCatalogRoutingModule} from './ads-catalog-routing.module';
import {AdsCatalogComponent} from './ads-catalog.component';
import {AdCatalogListComponent} from './ad-catalog-list/ad-catalog-list.component';


@NgModule({
  declarations: [
    AdsCatalogComponent,
    AdCatalogListComponent
  ],
  imports: [
    AdsModule,
    AdsCatalogRoutingModule
  ],
  providers: [
    AdService
  ],
  exports: []
})

export class AdsCatalogModule {
}
