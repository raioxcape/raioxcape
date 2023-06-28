import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Enigma } from 'src/app/classes/Enigma';
import { Jogo } from 'src/app/classes/Jogo';
import { PortaCaminho } from 'src/app/classes/PortaCaminho';
import { JogoService } from 'src/app/service/jogo-service';

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.scss']
})
export class QuizComponent implements OnInit {

  jogoId!: number;
  portaCaminhoEscolhida!: PortaCaminho;
  categoria: string;
  jogo: Jogo;
  enigmas: Enigma[];
  perguntaAtual!: Enigma;
  indiceAtual: number = 0;

  constructor(private route: ActivatedRoute, private jogoService: JogoService,
    private router: Router, private toastr: ToastrService, private _formBuilder: FormBuilder) {
    this.categoria = "";
    this.jogo = new Jogo();
    this.enigmas = [];
  }
  
  proximaPergunta() {
    if (this.indiceAtual < this.enigmas.length - 1) {
      this.indiceAtual++;
      this.perguntaAtual = this.enigmas[this.indiceAtual + 1];
    }
  }

  salvarRespostas() {
    //Logica para salvar resposta


    this.proximaPergunta();
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

      this.jogoService.getJogo(this.jogoId).subscribe((response: any) => {
        console.log(response);
        console.log(this.categoria);
        if (response.status === "OK") {
          this.jogo.id = response.data.id;
          this.jogo.atualizadoEm = response.data.atualizadoEm;
          this.jogo.criadoEm = response.data.criadoEm;
          this.enigmas = response.data.enigmas.filter((enigma: Enigma) => enigma.portaCaminho === this.portaCaminhoEscolhida);
          console.log(this.enigmas);
          this.jogo.pontos = response.data.pontos;
          this.perguntaAtual = this.enigmas[this.indiceAtual];
          console.log(this.jogo);
        } else {
          this.toastr.error("Erro ao trazer os dados do jogo!", "Erro");
        }
      });
    });
    
  }
}
