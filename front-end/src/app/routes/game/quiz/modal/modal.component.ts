import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.scss']
})
export class ModalComponent implements OnInit {

  jogoId!: number;

  constructor(private router: Router, private route: ActivatedRoute, public dialogRef: MatDialogRef<ModalComponent>) { }

  goToHistory() {
    this.dialogRef.close();
    this.router.navigate(['./../history']);
  }

  goToMenu() {
    this.dialogRef.close();
    window.history.back();
  }

  ngOnInit() {
    this.route.params.subscribe(param => {
      this.jogoId = param['id'];
      console.log(this.jogoId);
    });
  }
}
