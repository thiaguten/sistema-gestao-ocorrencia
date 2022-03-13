import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first, Observable } from 'rxjs';

import { Servico } from '../model/servico';

@Injectable({
  providedIn: 'root'
})
export class ServicoService {

  private readonly API = '/assets/mock_servicos.json';
  //private readonly API = '/api/v1/servicos';

  constructor(private httpClient: HttpClient) { }

  listarServicos(): Observable<Servico[]> {
    return this.httpClient.get<Servico[]>(this.API)
      .pipe(
        first()
        //tap(servicos => console.log(servicos))
      );
  }
}
