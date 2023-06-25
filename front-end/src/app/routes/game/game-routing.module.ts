import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GameComponent } from './game.component';
import { RulesComponent } from './rules/rules.component';
import { QuizComponent } from './quiz/quiz.component';

const routes: Routes = [
  {
    path: '',
    component: GameComponent
  },
  {
    path: 'rules',
    component: RulesComponent
  },
  {
    path: 'quiz',
    component: QuizComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GameRoutingModule { }
