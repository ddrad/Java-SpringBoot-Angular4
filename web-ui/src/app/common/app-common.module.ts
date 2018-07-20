import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {DropdownDirectiveDirective} from '../directives/dropdown-directive.directive';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { UploadFileComponent } from './components/upload-file/upload-file.component';

@NgModule({
  declarations: [
    DropdownDirectiveDirective,
    UploadFileComponent
  ],
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    DropdownDirectiveDirective,
    UploadFileComponent
  ]
})
export class AppCommonModule {
}
