import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-criar-conta',
  templateUrl: './criar-conta.component.html',
  styleUrls: ['./criar-conta.component.scss']
})
export class CriarContaComponent implements OnInit {

  hide = true;
  cadastroForm: FormGroup;

  constructor() {
    this.cadastroForm = this.createFormGroup();
  }

  ngOnInit(): void {
  }

  createFormGroup(): FormGroup {
    return new FormGroup({
      usuario: new FormControl('', [
        Validators.required
      ]),
      cpf: new FormControl('', {
        validators: [
          Validators.required,
          Validators.pattern(/^\d{11}$/)
        ],
        updateOn: "blur"
      }),
      email: new FormControl('', [
        Validators.required
      ]),
      primeiroNome: new FormControl('', [
        Validators.required
      ]),
      ultimoNome: new FormControl('', [
        Validators.required
      ]),
      senha: new FormControl('', [
        Validators.required
      ])
    });
  }

  onSubmit(): void {
    const usuario = this.cadastroForm.controls['usuario'].value;
    const cpf = this.cadastroForm.controls['cpf'].value;
    const email = this.cadastroForm.controls['email'].value;
    const primeiroNome = this.cadastroForm.controls['primeiroNome'].value;
    const ultimoNome = this.cadastroForm.controls['ultimoNome'].value;
    const senha = this.cadastroForm.controls['senha'].value;

    console.log('usuario', usuario);
    console.log('cpf', cpf);
    console.log('email', email);
    console.log('primeiroNome', primeiroNome);
    console.log('ultimoNome', ultimoNome);
    console.log('senha', senha);

    // TODO através de um service, chamar a API para salvar o novo usuário.
  }

  hasError(controlName: string, errorName: string): boolean {
    return this.cadastroForm.controls[controlName].touched &&
      this.cadastroForm.controls[controlName].hasError(errorName);
  }

}
