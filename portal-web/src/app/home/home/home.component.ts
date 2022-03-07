import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { catchError, Observable, of } from 'rxjs';
import { Ocorrencia } from 'src/app/ocorrencia/model/ocorrencia';
import { OcorrenciaService } from 'src/app/ocorrencia/service/ocorrencia.service';
import { ErrorDialogComponent } from 'src/app/shared/error/error-dialog/error-dialog.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  username = '';
  isAuthenticated = true;
  displayedColumns = ['codigo', 'data'];
  ocorrencias$: Observable<Ocorrencia[]>;
  ocorrenciasJson?: string;

  constructor(
    private ocorrenciaService: OcorrenciaService,
    public dialog: MatDialog
  ) {
    this.ocorrencias$ = this.ocorrenciaService.listarOcorrencias()
      .pipe(
        catchError(error => {
          console.log(error);
          //this.onError('Falha ao listar as ocorrências!');
          this.onError(`Falha ao listar as ocorrências! - Erro: ${error.message}`);
          //this.onError('Falha ao listar as ocorrências! - Erro: ' + JSON.stringify(error));
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
