import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';

import {AdDetailsComponent} from './ad-details/ad-details.component';
import {AdItemComponent} from './ad-item/ad-item.component';
import {AdStartComponent} from './ad-start/ad-start.component';
import {AdEditComponent} from './ad-edit/ad-edit.component';
import {AppCommonModule} from '../common/app-common.module';
import {AdFilterPipe} from "./pipes/ad-filter.pipe";


@NgModule({
  declarations: [
    AdDetailsComponent,
    AdItemComponent,
    AdStartComponent,
    AdEditComponent,
    AdFilterPipe
  ],
  imports: [
    RouterModule,
    AppCommonModule
  ],
  exports: [
    AppCommonModule,
    AdFilterPipe,
    AdItemComponent
  ]
})

export class AdsModule {
}
