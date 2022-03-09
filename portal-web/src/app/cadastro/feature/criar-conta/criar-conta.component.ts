import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-criar-conta',
  templateUrl: './criar-conta.component.html',
  styleUrls: ['./criar-conta.component.scss']
})
export class CriarContaComponent implements OnInit {

  hide = true;
  cadastroForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    // this.cadastroForm = this.createFormGroup();

    this.cadastroForm = this.formBuilder.group({
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

  ngOnInit(): void {
  }

  createFormGroup(): FormGroup {
    return new FormGroup({
      usuario: new FormControl('', Validators.required),
      cpf: new FormControl('', {
        validators: [
          Validators.required,
          Validators.pattern(/^\d{11}$/)
        ],
        updateOn: 'blur'
      }),
      email: new FormControl('', [
        Validators.required,
        Validators.email
      ]),
      primeiroNome: new FormControl('', Validators.required),
      ultimoNome: new FormControl('', Validators.required),
      senha: new FormControl('', Validators.required)
    });
  }

  onSubmit(): void {
    const usuario = this.getValue('usuario');
    const cpf = this.getValue('cpf');
    const email = this.getValue('email');
    const primeiroNome = this.getValue('primeiroNome');
    const ultimoNome = this.getValue('ultimoNome');
    const senha = this.getValue('senha');

    console.log('usuario', usuario);
    console.log('cpf', cpf);
    console.log('email', email);
    console.log('primeiroNome', primeiroNome);
    console.log('ultimoNome', ultimoNome);
    console.log('senha', senha);

    const isValid = this.cadastroForm.valid;
    console.log('isValid', isValid);

    // TODO através de um service, chamar a API para salvar o novo usuário.
  }

  hasError(controlName: string, errorName: string): boolean {
    return this.cadastroForm.controls[controlName].hasError(errorName) &&
      this.cadastroForm.controls[controlName].touched;
  }

  getValue(controlName: string) {
    // return this.cadastroForm.get(controlName)?.value;
    return this.cadastroForm.controls[controlName].value;
  }

}
