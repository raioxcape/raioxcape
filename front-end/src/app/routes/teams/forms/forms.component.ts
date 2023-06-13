import { Component } from '@angular/core';
import { NgModel } from '@angular/forms';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-forms',
  templateUrl: './forms.component.html',
  styleUrls: ['./forms.component.scss']
})
export class FormsComponent {

  quantity: number;
  integrantes: string[];

  constructor(){
    this.quantity = 1;
    this.integrantes = [''];

   }

   increment() {
    if (this.quantity < 10) {
      this.quantity++;
      this.integrantes.push(''); // Adiciona um novo campo vazio para o novo integrante
    }
  }
  
  decrement() {
    if (this.quantity > 1) {
      this.quantity--;
      this.integrantes.pop(); // Remove o campo do último integrante
    }
  }

  saveTeam(){
    console.log("ENVIOU!");
  }
}
