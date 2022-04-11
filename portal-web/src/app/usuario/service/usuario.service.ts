import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

import { Usuario } from '../model/usuario';

export const UsuariosAPI: string = `${environment.api_usuarios_base_url}/api/v1/usuarios`;

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private readonly API_USUARIOS = UsuariosAPI;

  constructor(private httpClient: HttpClient) { }

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

}
