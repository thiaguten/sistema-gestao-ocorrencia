import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { catchError, Observable, of } from 'rxjs';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.scss']
})
export class CadastroComponent implements OnInit {
  hide = true;
  cadastroForm: FormGroup;

  constructor(public dialog: MatDialog) {
    this.cadastroForm = new FormGroup({
      usuario: new FormControl('', [
        Validators.required
      ]),
      cpf: new FormControl('', {
        validators: [
          Validators.required,
          // only positive numbers
          Validators.pattern(/^\d+$/)
        ],
        updateOn: "blur"
      }),
      email: new FormControl('', [
        Validators.required
      ]),
      primeroNome: new FormControl('', [
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

  ngOnInit(): void {
  }

  onSubmit(): void {
    // const fromValue = this.cadastroForm.controls['from'].value;
    // const toValue = this.cadastroForm.controls['to'].value;
    // const quantityValue = this.cadastroForm.controls['quantity'].value;

    // const currencyConversion$: Observable<CurrencyConversion> = this.currencyService.getCurrencyConversion(fromValue, toValue, quantityValue);

    // currencyConversion$
    //   .pipe(
    //     catchError(error => {
    //       // console.log(error);
    //       this.onError('Fail to get currency conversion response')
    //       return of({});
    //     })
    //   )
    //   .subscribe(currencyConversion =>
    //     this.currencyConversionJson = JSON.stringify(currencyConversion)
    //   );
  }

  onError(errorMessage: string): void {
    // this.dialog.open(ErrorDialogComponent, {
    //   data: errorMessage
    // });
  }

  hasError(controlName: string, errorName: string): boolean {
    // return this.cadastroForm.controls[controlName].touched &&
    //   this.cadastroForm.controls[controlName].hasError(errorName);
    return false;
  }

}
