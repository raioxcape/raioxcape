import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HistoryComponent } from 'src/app/routes/history/history.component';
import { MatDialogModule } from '@angular/material/dialog';


@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.scss']
})
export class ModalComponent implements OnInit {

  jogoId!: number;

  constructor(private router: Router, private route: ActivatedRoute) { }

  goToHistory() {
    this.router.navigate(['./../history']);
  }

  goToMenu() {
    window.history.back();
  }

  ngOnInit() {
    this.route.params.subscribe(param => {
      this.jogoId = param['id'];
      console.log(this.jogoId);
    });
  }
}
