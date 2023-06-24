import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AppMaterialModule } from 'src/app/shared/app-material/app-material.module';
import { FormBuilder, FormsModule, NgModel, Validators } from '@angular/forms';
import { HeaderComponent } from '../header/header.component';
import { HeaderModule } from '../header/header.module';
import { HistoryComponent } from './history.component';
import { HistoryRoutingModule } from './history-routing.module';


@NgModule({
  declarations: [
    HistoryComponent
 ],
  imports: [
    AppMaterialModule,
    HistoryRoutingModule
  ], 
  exports: [
  ]
})
export class HistoryModule { }
