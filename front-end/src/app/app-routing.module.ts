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
    loadChildren: () => import('./routes/teams/teams.module').then(m => m.TeamsModule)
  },
  {
    path: 'history',
    loadChildren: () => import('./routes/history/history.module').then(m => m.HistoryModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
