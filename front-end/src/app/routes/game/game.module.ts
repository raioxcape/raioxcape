import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GameRoutingModule } from './game-routing.module';
import { GameComponent } from './game.component';
import { AppMaterialModule } from 'src/app/shared/app-material/app-material.module';
import { RulesComponent } from '../rules/rules.component';
import { QuizComponent } from './quiz/quiz.component';
import { ModalComponent } from './quiz/modal/modal.component';
import { AlertComponent } from './quiz/modal/alert.component';



@NgModule({
  declarations: [
    GameComponent,
    RulesComponent,
    QuizComponent,
    ModalComponent, 
    AlertComponent
  ],
  imports: [
    CommonModule,
    GameRoutingModule,
    AppMaterialModule
  ]
})
export class GameModule { }
