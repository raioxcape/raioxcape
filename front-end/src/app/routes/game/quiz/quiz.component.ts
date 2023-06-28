import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PortaCaminho } from 'src/app/classes/PortaCaminho';
import { JogoService } from 'src/app/service/jogo-service';

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.scss']
})
export class QuizComponent implements OnInit {

  jogoId!: number;
  portaCaminhoEscolhida! : PortaCaminho;
  categoria :string;

  constructor(private route: ActivatedRoute, private jogoService : JogoService,
    private router: Router) {
      this.categoria = "";
  }

  ngOnInit() {
    this.route.params.subscribe(param => {
      this.jogoId = param['id'];
      this.portaCaminhoEscolhida = param['portaCaminho'];
      let teste = param['portaCaminho'];

      if (this.portaCaminhoEscolhida === Object.entries(PortaCaminho).find(([_, v]) => v === PortaCaminho.CABECA_E_PESCOCO)![0]) {
        this.categoria = PortaCaminho.CABECA_E_PESCOCO;
      } else if (this.portaCaminhoEscolhida === Object.entries(PortaCaminho).find(([_, v]) => v === PortaCaminho.TORAX)![0]) {
        this.categoria = PortaCaminho.TORAX;
      } else if (this.portaCaminhoEscolhida === Object.entries(PortaCaminho).find(([_, v]) => v === PortaCaminho.ABDOMEN)![0]) {
        this.categoria = PortaCaminho.ABDOMEN;
      } else if (this.portaCaminhoEscolhida === Object.entries(PortaCaminho).find(([_, v]) => v === PortaCaminho.MUSCULO_ESQUELETICO)![0]) {
        this.categoria = PortaCaminho.MUSCULO_ESQUELETICO;
      }
      

      this.jogoService.getJogo(this.jogoId).subscribe((data: any) => {
        console.log(data);
      });
    });
  }
}
