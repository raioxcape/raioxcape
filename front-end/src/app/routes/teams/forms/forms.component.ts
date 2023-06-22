import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { EquipeService } from 'src/app/service/equipe-service';
import { ActivatedRoute } from '@angular/router';
import { EquipeCreationDTO } from 'src/app/classes/dto/EquipeCreationDTO';
import { IntegranteCreationDTO } from 'src/app/classes/dto/IntegranteCreationDTO';


@Component({
  selector: 'app-forms',
  templateUrl: './forms.component.html',
  styleUrls: ['./forms.component.scss']
})
export class FormsComponent implements OnInit {

  equipe: EquipeCreationDTO;
  integrantes: IntegranteCreationDTO[];
  quantidadeIntegrantes: number;
  submitted: boolean;
  equipeRec: string;

  constructor(
    private equipeService: EquipeService, private http: HttpClient, private formBuilder: FormBuilder,
    private toastr: ToastrService, private route: ActivatedRoute
  ) {
    this.equipe = new EquipeCreationDTO();
    this.integrantes = [new IntegranteCreationDTO()];
    this.quantidadeIntegrantes = 1;
    this.submitted = false;
    this.equipeRec = '';

    this.formBuilder.group({
      nomeEquipe: ['', Validators.required],
      integrantes: this.formBuilder.array([], Validators.required)
    });
  }

  adicionarIntegrante(): void {
    this.integrantes = new Array(this.quantidadeIntegrantes).fill('').map(() => new IntegranteCreationDTO());
  }

  saveEquipe(form: any) {
    console.log(form);

    if (form.valid) {
      this.equipe.nome = form.value.nomeEquipe;
      this.equipe.integrantes = this.integrantes.map((integrante: IntegranteCreationDTO) => integrante.nome);

      this.equipeService.saveEquipe(this.equipe).subscribe({
        next: (response) => {
          if (response.status === 'CREATED' && response.error === null) {
            this.toastr.success('A equipe foi cadastrada com sucesso!');
            form.reset();
          }

          console.log(response);
        },
        error: (error) => {
          this.toastr.success('Não foi possível cadastrar a equipe.', error);
          console.error(error);
        }
      });
    } else {
      this.toastr.warning('Preencha todos os campos do formulário.');
    }
  }

  ngOnInit(): void {

  }
};
