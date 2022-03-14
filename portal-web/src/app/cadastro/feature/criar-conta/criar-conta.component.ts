import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MessageSnackBarComponent } from 'src/app/shared/component/message-snack-bar/message-snack-bar.component';

@Component({
  selector: 'app-criar-conta',
  templateUrl: './criar-conta.component.html',
  styleUrls: ['./criar-conta.component.scss']
})
export class CriarContaComponent implements OnInit {

  hidePassword = true;
  cadastroForm: FormGroup;

  constructor(
    private snackBar: MatSnackBar,
    private formBuilder: FormBuilder
    ) {
    this.cadastroForm = this.createFormGroup();
  }

  ngOnInit(): void {
  }

  createFormGroup(): FormGroup {
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

  onSubmit(): void {
    const usuario = this.usuarioFormControl?.value;
    const cpf = this.cpfFormControl?.value;
    const email = this.emailFormControl?.value;
    const primeiroNome = this.primeiroNomeFormControl?.value;
    const ultimoNome = this.ultimoNomeFormControl?.value;
    const senha = this.senhaFormControl?.value;

    console.log('usuario', usuario);
    console.log('cpf', cpf);
    console.log('email', email);
    console.log('primeiroNome', primeiroNome);
    console.log('ultimoNome', ultimoNome);
    console.log('senha', senha);

    console.log('cadastroFormIsValid', this.cadastroForm.valid);
    // TODO através de um service, chamar a API para salvar o novo usuário.
    this.onSuccess('Usuário cadastrado com sucesso!');
  }

  hasError(controlName: string, errorName: string): boolean {
    const formControl = this.cadastroForm.controls[controlName];
    return formControl.touched && formControl.hasError(errorName);
  }

  getFormControl(controlName: string) {
    return this.cadastroForm.get(controlName);
    //return this.cadastroForm.controls[controlName];
  }

  onSuccess(successMessage: string): void {
    this.snackBar.openFromComponent(MessageSnackBarComponent, {
      data: {
        message: successMessage,
        //action: 'Fechar'
      },
      horizontalPosition: 'center',
      verticalPosition: 'top',
      duration: 10 * 1000,
      panelClass: ['green-snackbar']
    });
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
