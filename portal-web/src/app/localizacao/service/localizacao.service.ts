import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

import { Endereco } from '../model/endereco';

export const ReadonlyEmptyEndereco: Readonly<Endereco> = {
  cep: '',
  logradouro: '',
  bairro: '',
  localidade: '',
  uf: '',
  complemento: ''
};

@Injectable({
  providedIn: 'root'
})
export class LocalizacaoService {

  private readonly API_ENDERECOS = `${environment.api_localizacao_base_url}/api/v1/enderecos`;

  constructor(private httpClient: HttpClient) { }

  getEnderecoByCEP(cepCode: number): Observable<Endereco> {
    //return this.httpClient.get<Endereco>(this.API)
    return this.httpClient.get<Endereco>(`${this.API_ENDERECOS}/hateoas/cep/${cepCode}`)
      .pipe(
        first(),
        //tap(console.log)
      );
  }

}
