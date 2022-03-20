import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first, map, Observable } from 'rxjs';

import { Servico } from '../model/servico';

@Injectable({
  providedIn: 'root'
})
export class ServicoService {

  private readonly API_SERVICOS = '/api/v1/servicos';

  constructor(private httpClient: HttpClient) { }

  listarServicos(): Observable<Servico[]> {
    return this.httpClient.get<any>(this.API_SERVICOS)
      .pipe(
        first(),
        //tap(console.log),
        map(({ _embedded }) => _embedded.servicoDTOList)
      );
  }
}
