import { Injectable } from '@angular/core';
import { OAuthService } from 'angular-oauth2-oidc';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private _userProfile: object = {};
  // private isAuthenticatedSubject$ = new BehaviorSubject<boolean>(false);

  constructor(private oauthService: OAuthService) { }

  // isAuthenticated(): Observable<boolean> {
  //   return this.isAuthenticatedSubject$.asObservable();
  // }

  // setAuthenticated(isAuthenticated: boolean): void {
  //   this.isAuthenticatedSubject$.next(isAuthenticated);
  // }

  isLoggedIn(): boolean {
    return this.hasValidAccessToken && this.hasValidIdToken;
  }

  login(): void {
    // this.oauthService.initImplicitFlowInternal();
    // this.oauthService.initCodeFlow();
    this.oauthService.initLoginFlow();
  }

  logout(): void {
    // this.oauthService.logOut();
    this.oauthService.revokeTokenAndLogout();
  }

  hasRole(role: string): boolean {
    const token = this.oauthService.getAccessToken();
    const payloadEncoded = token.split('.')[1];
    const payloadDecodedJSON = window.atob(payloadEncoded);
    const payloadDecoded = JSON.parse(payloadDecodedJSON);
    return payloadDecoded.realm_access.roles.indexOf(role) !== -1;
  }

  // GETTERS

  get userProfile(): object {
    return this._userProfile;
  }

  set userProfile(up: object) {
    this._userProfile = up;
  }

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

  get identityClaims() {
    // fix for Typescript error: TS7053.
    // define what kind of index type the object has. In this case it is a string based index.
    const claims: { [index: string]: any } = this.oauthService.getIdentityClaims();
    return claims;
  }

  get givenName() {
    const claims = this.identityClaims;
    if (!claims) return null;
    return claims['given_name'];
  }

  get preferredUsername() {
    const claims = this.identityClaims;
    if (!claims) return null;
    return claims['preferred_username'];
  }

  get familyName() {
    const claims = this.identityClaims;
    if (!claims) return null;
    return claims['family_name'];
  }

  get subject() {
    const claims = this.identityClaims;
    if (!claims) return null;
    return claims['sub'];
  }
}
