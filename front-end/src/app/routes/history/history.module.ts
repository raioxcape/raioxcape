import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AppMaterialModule } from 'src/app/shared/app-material/app-material.module';
import { FormBuilder, FormsModule, NgModel, Validators } from '@angular/forms';
import { HistoryComponent } from './history.component';
import { HistoryRoutingModule } from './history-routing.module';
import { HistoryReportComponent } from './report/report.component';


@NgModule({
  declarations: [
    HistoryComponent, 
    HistoryReportComponent
 ],
  imports: [
    CommonModule,
    AppMaterialModule,
    HistoryRoutingModule
  ], 
  exports: [
  ]
})
export class HistoryModule { }
