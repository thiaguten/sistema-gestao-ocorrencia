import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first, map, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

import { Servico } from '../model/servico';

@Injectable({
  providedIn: 'root'
})
export class ServicoService {

  private readonly API_SERVICOS = `${environment.api_servico_base_url}/api/v1/servicos`;

  constructor(private httpClient: HttpClient) { }

  listarServicos(): Observable<Servico[]> {
    return this.httpClient.get<any>(this.API_SERVICOS)
      .pipe(
        first(),
        //tap(console.log),
        map(({ _embedded }) => {
          if (_embedded) {
            return _embedded.servicoDTOList;
          }
          return [];
        })
      );
  }
}
