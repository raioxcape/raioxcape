import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TeamsComponent } from './teams.component';
import { FormsComponent } from './forms/forms.component';
import { ListComponent } from './list/list.component';

import { RulesComponent } from '../rules/rules.component';

const routes: Routes = [
  {
    path: '',
    component: TeamsComponent,
  },
  {
    path: 'forms',
    component: FormsComponent
  },
  {
    path: 'list',
    component: ListComponent
  },
  { 
    path: 'edit-team/:team', 
    component: FormsComponent 
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TeamsRoutingModule { }
