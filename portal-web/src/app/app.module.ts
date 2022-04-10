import { HttpClientModule, HttpClientXsrfModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { OAuthModule } from 'angular-oauth2-oidc';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './footer/footer.component';
import { EnderecosAPI } from './localizacao/service/localizacao.service';
import { LoginModule } from './login/login.module';
import { NavbarComponent } from './navbar/navbar.component';
import { OcorrenciasAPI } from './ocorrencia/service/ocorrencia.service';
import { ServicosAPI } from './servico/service/servico.service';
import { SharedModule } from './shared/shared.module';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    SharedModule,
    HttpClientModule,
    HttpClientXsrfModule,
    LoginModule,
    OAuthModule.forRoot({
      resourceServer: {
        allowedUrls: [
          ServicosAPI,
          OcorrenciasAPI,
          EnderecosAPI
        ],
        sendAccessToken: true
      }
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
