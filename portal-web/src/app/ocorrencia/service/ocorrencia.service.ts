import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

import { Ocorrencia } from '../model/ocorrencia';
import { Usuario } from '../model/usuario';

@Injectable({
  providedIn: 'root'
})
export class OcorrenciaService {

  private readonly API = '/assets/mock_ocorrencias.json';
  private readonly API_OCORRENCIAS = `${environment.api_ocorrencia_base_url}/api/v1/ocorrencias`;
  private readonly API_USUARIOS = `${environment.api_usuarios_base_url}/api/v1/usuarios`;

  constructor(private httpClient: HttpClient) { }

  listarOcorrencias(): Observable<Ocorrencia[]> {
    return this.httpClient.get<Ocorrencia[]>(this.API)
      .pipe(
        first(),
        //delay(500), // para testar o spinner de carregando na listagem na tela.
        //tap(ocorrencias => console.log('ocorrencias', ocorrencias))
      );
  }

  // listarUltimasXOcorrencias(count: number): Observable<Ocorrencia> {
  //   return this.httpClient.get<Ocorrencia>('')
  //     .pipe(
  //       take(count),
  //       //tap(ocorrencias => console.log('ocorrencias', ocorrencias))
  //     );
  // }

  criarUsuario(usuario: Usuario): Observable<Usuario> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.httpClient.post<Usuario>(this.API_USUARIOS, usuario, httpOptions)
      .pipe(
        first(),
        //tap((usuarioCriado: Usuario) => console.log('Usuário criado', usuarioCriado))
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
