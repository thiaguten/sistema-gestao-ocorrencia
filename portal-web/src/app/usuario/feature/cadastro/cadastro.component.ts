import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';
import { catchError, EMPTY } from 'rxjs';
import { MessageSnackBarComponent } from 'src/app/shared/component/message-snack-bar/message-snack-bar.component';
import { Usuario } from 'src/app/usuario/model/usuario';
import { UsuarioService } from 'src/app/usuario/service/usuario.service';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.scss']
})
export class CadastroComponent implements OnInit {

  hidePassword = true;
  cadastroForm: FormGroup;

  constructor(
    private snackBar: MatSnackBar,
    private formBuilder: FormBuilder,
    private usuarioService: UsuarioService
  ) {
    this.cadastroForm = this.createFormGroup();
  }

  ngOnInit(): void {
  }

  private createFormGroup(): FormGroup {
    return this.formBuilder.group({
      usuario: ['', Validators.required],
      cpf: ['', {
        validators: [
          Validators.required,
          Validators.pattern(/^\d{11}$/)
        ],
        updateOn: 'blur'
      }],
      email: ['', [
        Validators.required,
        Validators.email
      ]],
      primeiroNome: ['', Validators.required],
      ultimoNome: ['', Validators.required],
      senha: ['', Validators.required]
    });
  }

  private criarNovoUsuario(): Usuario {
    const usuario: Usuario = {
      nomeUsuario: this.usuarioFormControl?.value,
      cpf: this.cpfFormControl?.value,
      email: this.emailFormControl?.value,
      primeiroNome: this.primeiroNomeFormControl?.value,
      ultimoNome: this.ultimoNomeFormControl?.value,
      senha: this.senhaFormControl?.value,
      notificacaoEmailAtivo: false
    };
    return usuario;
  }

  onSubmit(): void {
    if (this.cadastroForm.valid) {
      const usuario: Usuario = this.criarNovoUsuario();
      this.usuarioService.criarUsuario(usuario)
        .pipe(
          catchError((error: HttpErrorResponse) => {
            this.onError(`Falha ao cadastrar usuário! - Erro: ${error.message}`);
            //return of({});
            return EMPTY;
          })
        )
        .subscribe((u: Usuario) => {
          this.onSuccess(`Usuário cadastrado com sucesso! - ID: ${u.id} - IDP_ID: ${u.idpId}`);
          this.cadastroForm.reset();
        });
    }
  }

  hasError(controlName: string, errorName: string): boolean {
    const formControl = this.cadastroForm.controls[controlName];
    return formControl.touched && formControl.hasError(errorName);
  }

  private getFormControl(controlName: string) {
    return this.cadastroForm.get(controlName);
    //return this.cadastroForm.controls[controlName];
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

  // FORM CONTROL GETTERS

  get usuarioFormControl() {
    return this.getFormControl('usuario');
  }

  get cpfFormControl() {
    return this.getFormControl('cpf');
  }

  get emailFormControl() {
    return this.getFormControl('email');
  }

  get primeiroNomeFormControl() {
    return this.getFormControl('primeiroNome');
  }

  get ultimoNomeFormControl() {
    return this.getFormControl('ultimoNome');
  }

  get senhaFormControl() {
    return this.getFormControl('senha');
  }

}
