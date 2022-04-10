import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first, map, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

import { Ocorrencia } from '../model/ocorrencia';

export const OcorrenciasAPI: string = `${environment.api_ocorrencia_base_url}/api/v1/ocorrencias`;

@Injectable({
  providedIn: 'root'
})
export class OcorrenciaService {

  private readonly API_OCORRENCIAS = OcorrenciasAPI;

  constructor(private httpClient: HttpClient) { }

  listarOcorrencias(): Observable<Ocorrencia[]> {
    return this.httpClient.get<any>(this.API_OCORRENCIAS)
      .pipe(
        first(),
        //delay(500), // para testar o spinner de carregando na listagem na tela.
        //tap(ocorrencias => console.log('ocorrencias', ocorrencias)),
        map(({ _embedded }) => {
          if (_embedded) {
            return _embedded.ocorrenciaDTOList;
          }
          return [];
        })
      );
  }

  criarOcorrencia(ocorrencia: Ocorrencia): Observable<Ocorrencia> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.httpClient.post<Ocorrencia>(this.API_OCORRENCIAS, ocorrencia, httpOptions)
      .pipe(
        first(),
        //tap((ocorrenciaCriada: Ocorrencia) => console.log('Ocorrência criada', ocorrenciaCriada))
      );
  }

}
