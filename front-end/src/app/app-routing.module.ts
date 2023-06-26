import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './routes/home/home.component';

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
  },
  {
    path: 'game',
    loadChildren: () => import('./routes/game/game.module').then(m => m.GameModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
