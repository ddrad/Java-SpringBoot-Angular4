import {NgModule} from '@angular/core';
import { ShoppingListService } from './shopping-list.service';
import { ShoppingListComponent } from './shopping-list.component';
import { AppCommonModule } from '../common/app-common.module';

@NgModule({
  declarations: [
    ShoppingListComponent,
  ],
  imports: [
    AppCommonModule,
  ],
  providers: [
    ShoppingListService
  ]
})
export class ShoppingListModule {
}
