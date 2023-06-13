import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './routes/home/home.component';
import { TeamsComponent } from './routes/teams/teams.component';
import { FormsComponent } from './routes/teams/forms/forms.component';
import { ListComponent } from './routes/teams/list/list.component';
import { HistoryComponent } from './routes/history/history.component';
import { HistoryReportComponent } from './routes/history/report/report.component';
import { HeaderComponent } from './routes/header/header.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'teams',
    component: TeamsComponent
  },
  {
    path: 'teams/forms',
    component: FormsComponent
  },
  {
    path: 'teams/list',
    component: ListComponent
  },
  {
    path: 'history',
    component: HistoryComponent
  },
  {
    path: 'history/report',
    component: HistoryReportComponent
  },
  {
    path: 'header',
    component: HeaderComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
