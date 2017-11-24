import {NgModule} from '@angular/core';
import {AppCommonModule} from 'app/common/app-common.module';
import {ShoppingListComponent} from 'app/shopping-list/shopping-list.component';

@NgModule({
  declarations: [
    ShoppingListComponent,
  ],
  imports: [
    AppCommonModule,
  ]
})
export class ShoppingListModule {
}
