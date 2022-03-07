import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { delay, first, Observable, take, tap } from 'rxjs';

import { Ocorrencia } from '../model/ocorrencia';

@Injectable({
  providedIn: 'root'
})
export class OcorrenciaService {

  private readonly API = '/assets/mock_ocorrencias.json';
  //private readonly API = 'http://localhost:4200/api/v1/ocorrencias';

  constructor(private httpClient: HttpClient) { }

  listarOcorrencias(): Observable<Ocorrencia[]> {
    return this.httpClient.get<Ocorrencia[]>(this.API)
      .pipe(
        first(),
        delay(500), // para testar o spinner de carregando na listagem na tela.
        //tap(ocorrencias => console.log(ocorrencias))
      );
  }

  // listarUltimasXOcorrencias(count: number): Observable<Ocorrencia> {
  //   return this.httpClient.get<Ocorrencia>('')
  //     .pipe(
  //       take(count),
  //       //tap(ocorrencias => console.log(ocorrencias))
  //     );
  // }

}
