import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HistoryComponent } from 'src/app/routes/history/history.component';
import { GameComponent } from '../../game.component';

@Component({
  selector: 'app-modal',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.scss']
})
export class AlertComponent implements OnInit {

  jogoId!: number;

  constructor(private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(param => {
      this.jogoId = param['id'];
      console.log(this.jogoId);
    });
  }

  goToMenu() {
    window.history.back();
  }
}
