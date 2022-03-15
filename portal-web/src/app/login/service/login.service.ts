import { Injectable } from '@angular/core';
import { OAuthService } from 'angular-oauth2-oidc';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private oauthService: OAuthService) { }

  login(): void {
    // this.oauthService.initImplicitFlowInternal();
    // this.oauthService.initCodeFlow();
    this.oauthService.initLoginFlow();
  }

  logout(): void {
    // this.oauthService.logOut();
    this.oauthService.revokeTokenAndLogout();
  }

  isLogged(): boolean {
    return this.hasValidAccessToken && this.hasValidIdToken;
  }

  // GETTERS

  get hasValidAccessToken() {
    return this.oauthService.hasValidAccessToken();
  }

  get hasValidIdToken() {
    return this.oauthService.hasValidIdToken();
  }

  get id_token() {
    return this.oauthService.getIdToken();
  }

  get access_token() {
    return this.oauthService.getAccessToken();
  }

  get id_token_expiration() {
    return this.oauthService.getIdTokenExpiration();
  }

  get access_token_expiration() {
    return this.oauthService.getAccessTokenExpiration();
  }

  get givenName() {
    // fix for Typescript error: TS7053.
    // define what kind of index type the object has. In this case it is a string based index.
    const claims: { [index: string]: any } = this.oauthService.getIdentityClaims();
    if (!claims) return null;
    return claims['given_name'];
  }

  get preferredUsername() {
    // fix for Typescript error: TS7053.
    // define what kind of index type the object has. In this case it is a string based index.
    const claims: { [index: string]: any } = this.oauthService.getIdentityClaims();
    if (!claims) return null;
    return claims['preferred_username'];
  }

  get familyName() {
    // fix for Typescript error: TS7053.
    // define what kind of index type the object has. In this case it is a string based index.
    const claims: { [index: string]: any } = this.oauthService.getIdentityClaims();
    if (!claims) return null;
    return claims['family_name'];
  }

}
