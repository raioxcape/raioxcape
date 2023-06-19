import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AppMaterialModule } from 'src/app/shared/app-material/app-material.module';
import { FormBuilder, FormsModule, NgModel, Validators } from '@angular/forms';
import { HeaderComponent } from '../header/header.component';
import { HeaderModule } from '../header/header.module';
import { HistoryComponent } from './history.component';


@NgModule({
  declarations: [
 ],
  imports: [
    AppMaterialModule,
  ], 
  exports: [
  ]
})
export class HistoryModule { }
