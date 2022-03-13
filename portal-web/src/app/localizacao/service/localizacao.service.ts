import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first, Observable, tap } from 'rxjs';

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

  //private readonly API = '/assets/mock_endereco.json';
  private readonly API = '/api/v1/endereco';

  constructor(private httpClient: HttpClient) { }

  getEnderecoByCEP(cepCode: number): Observable<Endereco> {
    //return this.httpClient.get<Endereco>(this.API)
    return this.httpClient.get<Endereco>(`${this.API}/hateoas/cep/${cepCode}`)
      .pipe(
        first(),
        tap(console.log)
      );
  }

}
