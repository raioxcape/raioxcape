import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Enigma } from 'src/app/classes/Enigma';
import { Jogo } from 'src/app/classes/Jogo';
import { NivelDificuldade } from 'src/app/classes/NivelDificuldade';
import { PortaCaminho } from 'src/app/classes/PortaCaminho';
import { JogoService } from 'src/app/service/jogo-service';
import { RulesComponent } from '../../rules/rules.component';
import { MatDialog } from '@angular/material/dialog';
import { EnigmaUpdateDTO } from 'src/app/classes/dto/EnigmaUpdateDTO';

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
  respostasSelecionadas: number[] = [];
  nivelDificuldade!: NivelDificuldade;
  dificuldade: string = "";
  tempoInicial: number = 0;
  tempoResposta: number = 0;
  pontuacao : number = 0;

  constructor(private route: ActivatedRoute, private jogoService: JogoService,
    private router: Router, private toastr: ToastrService, private _formBuilder: FormBuilder,
    public dialog: MatDialog) {
    this.categoria = "";
    this.jogo = new Jogo();
    this.enigmas = [];
  }

  openDialog() {
    const dialogRef = this.dialog.open(RulesComponent);
  }

  iniciarTempoResposta() {
    this.tempoInicial = Date.now();
  }

  pararTempoResposta() {
    const tempoAtual = Date.now();
    this.tempoResposta = (tempoAtual - this.tempoInicial) / 1000;
    console.log("Tempo de resposta:", this.tempoResposta, "segundos");
  }

  proximaPergunta() {
    this.iniciarTempoResposta();

    if (this.indiceAtual < this.enigmas.length - 1) {
      this.indiceAtual++;
      this.perguntaAtual = this.enigmas[this.indiceAtual + 1];
    }
    this.verificaDificuldade(this.perguntaAtual);
  }

  verificarResposta(respostas: number[]) {
    console.log(respostas);
    this.pararTempoResposta();

    let payload : EnigmaUpdateDTO = new EnigmaUpdateDTO();
    payload.tempoDecorridoSolucaoSegundos = this.tempoResposta;
    payload.idsOpcoesRespostaEquipe = respostas;
    

    this.jogoService.updateEnigmaJogo(this.perguntaAtual.id, this.jogoId, payload).subscribe((response: any) => { 
      console.log(response);


    });

    this.proximaPergunta();
    this.respostasSelecionadas = [];

    const checkboxes = document.querySelectorAll('.opcaoResposta input[type="checkbox"]');
    checkboxes.forEach((checkbox: Element) => {
      (checkbox as HTMLInputElement).checked = false;
    });
  }

  salvarResposta(index: number) {
    const idOpcaoSelecionada = this.perguntaAtual.opcoesResposta[index].id;
    if (this.respostasSelecionadas.includes(idOpcaoSelecionada)) {
      this.respostasSelecionadas = this.respostasSelecionadas.filter(id => id !== idOpcaoSelecionada);
    } else {
      this.respostasSelecionadas.push(idOpcaoSelecionada);
    }
  }


  verificaDificuldade(perguntaAtual: Enigma) {
    if (this.perguntaAtual.nivelDificuldade === Object.entries(NivelDificuldade).find(([_, v]) => v === NivelDificuldade.FACIL)![0]) {
      this.dificuldade = 'Fácil';
    } else if (this.perguntaAtual.nivelDificuldade === Object.entries(NivelDificuldade).find(([_, v]) => v === NivelDificuldade.MEDIO)![0]) {
      this.dificuldade = 'Médio';
    } else if (this.perguntaAtual.nivelDificuldade === Object.entries(NivelDificuldade).find(([_, v]) => v === NivelDificuldade.DIFICIL)![0]) {
      this.dificuldade = 'Difícil';
    }
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

        if (response.status === "OK") {
          this.jogo.id = response.data.id;
          this.jogo.atualizadoEm = response.data.atualizadoEm;
          this.jogo.criadoEm = response.data.criadoEm;
          this.enigmas = response.data.enigmas.filter((enigma: Enigma) => enigma.portaCaminho === this.portaCaminhoEscolhida);
          console.log(this.enigmas);

          this.jogo.pontos = response.data.pontos;
          this.perguntaAtual = this.enigmas[this.indiceAtual];
          this.verificaDificuldade(this.perguntaAtual);

          this.iniciarTempoResposta();
        } else {
          this.toastr.error("Erro ao trazer os dados do jogo!", "Erro");
        }
      });
    });
  }
}
