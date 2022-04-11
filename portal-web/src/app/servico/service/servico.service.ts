import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first, map, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

import { Servico } from '../model/servico';

export const ServicosAPI: string = `${environment.api_servico_base_url}/api/v1/servicos`;

@Injectable({
  providedIn: 'root'
})
export class ServicoService {

  private readonly API_SERVICOS = ServicosAPI;

  constructor(private httpClient: HttpClient) { }

  listarServicos(): Observable<Servico[]> {
    return this.httpClient.get<any>(this.API_SERVICOS)
      .pipe(
        first(),
        //tap(console.log),

        // obtem o objeto _embedded e o array de servico dentro dele.
        // nesse cenário também poderia usar o operador pluck conforme exemplo abaixo.
        map(({ _embedded }) => (_embedded.servicoDTOList || [])),

        //pluck('_embedded', 'servicoDTOList'),
        //map((servicos: Servico[]) => (servicos || []))
      );
  }
}
