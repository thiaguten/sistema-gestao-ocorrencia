import { Component } from '@angular/core';
import { AuthConfig, OAuthService } from 'angular-oauth2-oidc';
import { filter } from 'rxjs';
import { environment } from 'src/environments/environment';

import { LoginService } from './login/service/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'portal-web';

  private readonly authCodeFlowConfig: AuthConfig = {
    issuer: `${environment.idp_base_url}/realms/sgo`,
    redirectUri: window.location.origin + '/home',
    clientId: 'sgo-portal-web',
    responseType: 'code',
    scope: 'openid profile email offline_access',
    showDebugInformation: true
  };

  constructor(
    private oauthService: OAuthService,
    private loginService: LoginService
  ) {
    // // Observar os eventos oauth/oidc e locar no console para propósitos de debug.
    // this.oauthService.events.subscribe((e) => {
    //   console.log('oauth/oidc event', e);
    // });

    // // Observar os eventos oauth/oidc para setar variável
    // // que determinará se o usuário está autenticado.
    // this.oauthService.events
    //   .subscribe((_) => {
    //     this.loginService.setAuthenticated(this.oauthService.hasValidAccessToken());
    //   });

    // Observar evento oauth/oidc de recebimento de token e na sequência
    // invocar o endpoint de UserInfo para obter mais informações sobre o usuário.
    this.oauthService.events
      .pipe(filter((e) => ['token_received'].includes(e.type)))
      .subscribe((e) => this.oauthService.loadUserProfile()
        .then((up: object) => this.loginService.userProfile = up));

    // Iniciar fluxo de autorização via código do OAuth 2.0
    // extendido com o OpenID Connect (OIDC) através do escopo (openid).
    this.configureCodeFlow();
  }

  private configureCodeFlow(): void {
    this.oauthService.configure(this.authCodeFlowConfig);

    this.oauthService.loadDiscoveryDocumentAndTryLogin()
      .then(() => {
        if (this.oauthService.hasValidAccessToken() && this.oauthService.hasValidIdToken()) {

          // Invoca o endpoint UserIfo para obter informações sobre
          // o usuário logado e inicializar a variável userProfile.
          this.oauthService.loadUserProfile()
            .then((up: object) => this.loginService.userProfile = up);

          // // Obtém os escopos e os loga no console.
          // const scopes = this.oauthService.getGrantedScopes();
          // console.log('scopes', scopes);
        }
      });

    // Opcionalmente, inicia o processo de renovação de token em background
    // quando o mesmo estiver proximo de expirar.
    this.oauthService.setupAutomaticSilentRefresh();
  }

}
