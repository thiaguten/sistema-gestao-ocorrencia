import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';
import { catchError, EMPTY, Observable, of } from 'rxjs';
import { Endereco } from 'src/app/localizacao/model/endereco';
import { LocalizacaoService, ReadonlyEmptyEndereco } from 'src/app/localizacao/service/localizacao.service';
import { LoginService } from 'src/app/login/service/login.service';
import { Servico } from 'src/app/servico/model/servico';
import { ServicoService } from 'src/app/servico/service/servico.service';
import { MessageSnackBarComponent } from 'src/app/shared/component/message-snack-bar/message-snack-bar.component';

import { Ocorrencia } from '../../model/ocorrencia';
import { OcorrenciaService } from '../../service/ocorrencia.service';

@Component({
  selector: 'app-registrar-ocorrencia',
  templateUrl: './registrar-ocorrencia.component.html',
  styleUrls: ['./registrar-ocorrencia.component.scss']
})
export class RegistrarOcorrenciaComponent implements OnInit {

  registroForm: FormGroup;
  servicos$: Observable<Servico[]>;

  constructor(
    private snackBar: MatSnackBar,
    private formBuilder: FormBuilder,
    private servicoService: ServicoService,
    private ocorrenciaService: OcorrenciaService,
    private localizacaoService: LocalizacaoService,
    private loginService: LoginService
  ) {
    this.registroForm = this.createFormGroup();
    this.servicos$ = this.servicoService.listarServicos()
      .pipe(
        catchError(error => {
          console.log(error);
          return of([]);
        })
      );
  }

  ngOnInit(): void {
    this.onChangeInputCEP();
  }

  private createFormGroup(): FormGroup {
    return this.formBuilder.group({
      servico: ['', Validators.required],
      cep: ['', {
        validators: [
          Validators.required,
          Validators.pattern(/^\d{8}$/)
        ],
        updateOn: 'blur'
      }],
      estado: ['', Validators.required],
      cidade: ['', Validators.required],
      bairro: ['', Validators.required],
      endereco: ['', Validators.required],
      quantidadeIncidentes: ['', Validators.required],
      descricao: ['', Validators.required]
    });
  }

  hasError(controlName: string, errorName: string): boolean {
    const formControl = this.registroForm.controls[controlName];
    return formControl.touched && formControl.hasError(errorName);
  }

  private getFormControl(controlName: string) {
    return this.registroForm.get(controlName);
    //return this.registroForm.controls[controlName];
  }

  private criarNovaOcorrencia(): Ocorrencia {
    console.log('usuario', this.loginService.subject);
    console.log('servico', this.servicoFormControl?.value);
    console.log('cep', this.cepFormControl?.value);
    console.log('estado', this.estadoFormControl?.value);
    console.log('cidade', this.cidadeFormControl?.value);
    console.log('bairro', this.bairroFormControl?.value);
    console.log('endereco', this.enderecoFormControl?.value);
    console.log('quantidadeIncidentes', this.quantidadeFormControl?.value);
    console.log('descricao', this.descricaoFormControl?.value);

    const ocorrencia: Ocorrencia = {
      codigo: '',
      data: ''
    };
    return ocorrencia;
  }

  onSubmit(): void {
    if (this.registroForm.valid) {
      const ocorrencia: Ocorrencia = this.criarNovaOcorrencia();
      // this.ocorrenciaService.criarOcorrencia(ocorrencia)
      //   .pipe(
      //     catchError((error: HttpErrorResponse) => {
      //       this.onError(`Falha ao registrar ocorrência! - Erro: ${error.message}`);
      //       //return of({});
      //       return EMPTY;
      //     })
      //   )
      //   .subscribe((o: Ocorrencia) => {
      //     this.onSuccess(`Ocorrência registrada com sucesso! - Código: ${o.codigo}`);
      //     this.registroForm.reset();
      //   });
    }
  }

  private onSuccess(successMessage: string): void {
    this.mostrarSnapBar(this.createMatSnackBarConfig(
      {
        message: successMessage,
        //action: 'Fechar'
      },
      ['green-snackbar']
    ));
  }

  private onError(errorMessage: string): void {
    this.mostrarSnapBar(this.createMatSnackBarConfig(
      {
        message: errorMessage,
        //action: 'Fechar'
      },
      ['red-snackbar']
    ));
  }

  private createMatSnackBarConfig<D = any>(
    data?: D,
    panelClass?: string | string[]
  ): MatSnackBarConfig {
    return {
      data: data,
      horizontalPosition: 'center',
      verticalPosition: 'top',
      duration: 10 * 1000,
      panelClass: panelClass
    };
  }

  private mostrarSnapBar(config?: MatSnackBarConfig): void {
    this.snackBar.openFromComponent(MessageSnackBarComponent, config);
  }

  private onChangeInputCEP(): void {
    // Observar mudanças no campo de CEP para ativar a busca de endereço por CEP.
    this.cepFormControl?.valueChanges.subscribe(cepValue => {

      if (this.cepFormControl?.valid) {
        //console.log(`Buscando endereco para o CEP: ${cepValue}`);

        // Buscar endereco para o CEP informado.
        this.localizacaoService.getEnderecoByCEP(cepValue)
          .pipe(
            catchError(error => {
              console.warn(`Falha ao buscar o endereço para o CEP: ${cepValue} - Erro: ${error.message}`);
              //this.onError(`Falha ao buscar o endereço para o CEP: ${cepValue} - Erro: ${error.message}`);
              //return of(ReadonlyEmptyEndereco);
              return EMPTY;
            })
          )
          .subscribe((endereco: Endereco) => this.popularCamposLocalizacao(endereco))

      } else {
        this.popularCamposLocalizacao(ReadonlyEmptyEndereco);
      }

    });
  }

  private popularCamposLocalizacao(endereco: Endereco): void {
    if (endereco) {
      // Alterar o valor dos inputs dos outros form controls relacionados a localizacao.
      // https://angular.io/guide/reactive-forms#updating-parts-of-the-data-model

      // this.estadoFormControl?.setValue(endereco.uf);
      // this.cidadeFormControl?.setValue(endereco.localidade);
      // this.bairroFormControl?.setValue(endereco.bairro);
      // this.enderecoFormControl?.setValue(endereco.logradouro);

      this.registroForm.patchValue({
        estado: endereco.uf,
        cidade: endereco.localidade,
        bairro: endereco.bairro,
        endereco: endereco.logradouro
      });
    }
  }

  // FORM CONTROL GETTERS

  get servicoFormControl() {
    return this.getFormControl('servico');
  }

  get cepFormControl() {
    return this.getFormControl('cep');
  }

  get estadoFormControl() {
    return this.getFormControl('estado');
  }

  get cidadeFormControl() {
    return this.getFormControl('cidade');
  }

  get bairroFormControl() {
    return this.getFormControl('bairro');
  }

  get enderecoFormControl() {
    return this.getFormControl('endereco');
  }

  get quantidadeFormControl() {
    return this.getFormControl('quantidadeIncidentes');
  }

  get descricaoFormControl() {
    return this.getFormControl('descricao');
  }

}
