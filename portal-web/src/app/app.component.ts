import { Component } from '@angular/core';
import { AuthConfig, NullValidationHandler, OAuthService } from 'angular-oauth2-oidc';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'portal-web';
  userProfile: object = {};

  private readonly authCodeFlowConfig: AuthConfig = {
    issuer: 'http://localhost:8080/auth/realms/sgo',
    redirectUri: window.location.origin + '/index.html',
    clientId: 'sgo-portal-web',
    responseType: 'code',
    scope: 'openid profile email offline_access',
    showDebugInformation: true
  };

  constructor(private oauthService: OAuthService) {
    this.configureCodeFlow();
  }

  private configureCodeFlow(): void {
    // this.oauthService.events.subscribe(e => {
    //   console.log('oauth/oidc event', e);
    // });

    this.oauthService.configure(this.authCodeFlowConfig);

    this.oauthService.loadDiscoveryDocumentAndTryLogin()
      .then(() => {
        if (this.oauthService.hasValidAccessToken() && this.oauthService.hasValidIdToken()) {
          this.oauthService.loadUserProfile()
            .then((up: object) => this.userProfile = up);

          const scopes = this.oauthService.getGrantedScopes();
          console.log('scopes', scopes);
        }
      });

    // Optional
    this.oauthService.setupAutomaticSilentRefresh();
  }

}
