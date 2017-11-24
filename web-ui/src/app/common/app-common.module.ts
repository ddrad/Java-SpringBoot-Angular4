import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {DropdownDirectiveDirective} from '../directives/dropdown-directive.directive';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    DropdownDirectiveDirective
  ],
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    DropdownDirectiveDirective
  ]
})
export class AppCommonModule {
}
