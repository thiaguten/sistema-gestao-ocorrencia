import { Component } from '@angular/core';
import { AuthConfig, OAuthService } from 'angular-oauth2-oidc';
import { filter } from 'rxjs';

import { LoginService } from './login/service/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'portal-web';

  private readonly authCodeFlowConfig: AuthConfig = {
    issuer: 'http://localhost:8080/auth/realms/sgo',
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
    //// debug
    // this.oauthService.events.subscribe((e) => {
    //   console.log('oauth/oidc event', e);
    // });

    // this.oauthService.events
    //   .subscribe((_) => {
    //     this.loginService.setAuthenticated(this.oauthService.hasValidAccessToken());
    //   });

    this.oauthService.events
      .pipe(filter((e) => ['token_received'].includes(e.type)))
      .subscribe((e) => this.oauthService.loadUserProfile()
        .then((up: object) => this.loginService.userProfile = up));

    // iniciar fluxo oauth
    this.configureCodeFlow();
  }

  private configureCodeFlow(): void {
    this.oauthService.configure(this.authCodeFlowConfig);

    this.oauthService.loadDiscoveryDocumentAndTryLogin()
      .then(() => {
        if (this.oauthService.hasValidAccessToken() && this.oauthService.hasValidIdToken()) {

          this.oauthService.loadUserProfile()
            .then((up: object) => this.loginService.userProfile = up);

          const scopes = this.oauthService.getGrantedScopes();
          console.log('scopes', scopes);
        }
      });

    // Optional
    this.oauthService.setupAutomaticSilentRefresh();
  }

}
