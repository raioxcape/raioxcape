import { Component, Inject } from '@angular/core';

import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { EquipeUpdateDTO } from 'src/app/classes/dto/EquipeUpdateDTO';
import { EquipeService } from 'src/app/service/equipe-service';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.scss']
})
export class EditComponent {

  nomeEquipeAntigo : string;
  equipeDTO : EquipeUpdateDTO;

  constructor(
    public dialogRef: MatDialogRef<EditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private equipeService : EquipeService,
    private toastr: ToastrService) {
    this.nomeEquipeAntigo = data.nome;
    this.equipeDTO = new EquipeUpdateDTO;
   }


  editTeam(novoNome: string) {
    console.log(novoNome);
    console.log(this.nomeEquipeAntigo);
    this.equipeDTO.nome = novoNome;
    console.log(this.equipeDTO);

    if (novoNome != this.nomeEquipeAntigo) {
      this.equipeService.updateEquipe(this.nomeEquipeAntigo, this.equipeDTO).subscribe({
        next: (response) => {
          if (response.status === 'OK' && response.error === null) {
            this.toastr.success('A equipe foi atualizada com sucesso!');

          }
          console.log(response);
        },
        error: (error) => {
          this.toastr.error('Não foi possível editar a equipe.', error);
          console.error(error);
        }
      });

      this.dialogRef.close();
    } else {
      this.toastr.warning("Equipe inalterada, nome igual ao antigo.", "Atenção!");
    }
  }
}
