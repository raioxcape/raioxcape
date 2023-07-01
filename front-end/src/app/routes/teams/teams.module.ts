import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TeamsRoutingModule } from './teams-routing.module';
import { TeamsComponent } from './teams.component';
import { FormsComponent } from './forms/forms.component';
import { ListComponent } from './list/list.component';
import { AppMaterialModule } from 'src/app/shared/app-material/app-material.module';
import { FormBuilder, FormsModule, NgModel, Validators } from '@angular/forms';
import { EditComponent } from './list/edit/edit.component';


@NgModule({
  declarations: [
    TeamsComponent,
    FormsComponent,
    ListComponent,
    EditComponent
  ],
  imports: [
    CommonModule,
    TeamsRoutingModule,
    AppMaterialModule,
    FormsModule
  ], 
  exports: [
  ]
})
export class TeamsModule { }
