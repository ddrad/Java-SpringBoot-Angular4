import { NgModule } from '@angular/core';
import { AppCommonModule } from '../common/app-common.module';
import { CustomerInfoComponent } from './customer-info/customer-info.component';
import { ConfirmOrderRoutingModule } from './confirm-order-routing.module';

@NgModule({
  declarations: [
   CustomerInfoComponent
  ],
  imports: [
    AppCommonModule,
    ConfirmOrderRoutingModule
  ]
})
export class ConfirmOrderModule {
}
