import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import { CustomerInfoComponent } from "./customer-info/customer-info.component";


const authRouts: Routes = [
    {path: 'confirm-order', component: CustomerInfoComponent},
];

@NgModule({
  imports : [
    RouterModule.forChild(authRouts)
  ],
  exports : [
    RouterModule
  ]
})
export class ConfirmOrderRoutingModule {}