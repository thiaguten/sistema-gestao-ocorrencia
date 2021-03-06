import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { catchError, Observable, of } from 'rxjs';
import { ErrorDialogComponent } from 'src/app/shared/component/error-dialog/error-dialog.component';

import { Ocorrencia } from '../../model/ocorrencia';
import { OcorrenciaService } from '../../service/ocorrencia.service';

@Component({
  selector: 'app-listar-ocorrencia',
  templateUrl: './listar-ocorrencia.component.html',
  styleUrls: ['./listar-ocorrencia.component.scss']
})
export class ListarOcorrenciaComponent implements OnInit {

  displayedColumns = [
    'codigo',
    'servico',
    'situacao',
    'data'
  ];
  ocorrencias$: Observable<Ocorrencia[]>;
  ocorrenciasJson?: string;

  constructor(
    private ocorrenciaService: OcorrenciaService,
    public dialog: MatDialog
  ) {
    this.ocorrencias$ = this.ocorrenciaService.listarOcorrencias()
      .pipe(
        catchError(error => {
          //console.log(error);
          //this.onError('Falha ao listar as ocorrĂȘncias!');
          this.onError(`Falha ao listar as ocorrĂȘncias! - Erro: ${error.message}`);
          //this.onError('Falha ao listar as ocorrĂȘncias! - Erro: ' + JSON.stringify(error));
          return of([]);
        })
      );

    //this.ocorrencias$
    //  .subscribe(ocorrencias =>
    //    this.ocorrenciasJson = JSON.stringify(ocorrencias)
    //  );
  }

  ngOnInit(): void {
  }

  onError(errorMessage: string): void {
    this.dialog.open(ErrorDialogComponent, {
      data: {
        message: errorMessage
      }
    });
  }


}
