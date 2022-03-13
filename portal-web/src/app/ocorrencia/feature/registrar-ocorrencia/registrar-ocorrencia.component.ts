import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Servico } from 'src/app/servico/model/servico';
import { ServicoService } from 'src/app/servico/service/servico.service';

@Component({
  selector: 'app-registrar-ocorrencia',
  templateUrl: './registrar-ocorrencia.component.html',
  styleUrls: ['./registrar-ocorrencia.component.scss']
})
export class RegistrarOcorrenciaComponent implements OnInit {

  registroForm: FormGroup;
  // servicos?: Servico[];
  servicos$: Observable<Servico[]>;

  constructor(
    private formBuilder: FormBuilder,
    private servicoService: ServicoService
  ) {
    this.servicos$ = this.servicoService.listarServicos();

    // this.servicos$.subscribe(servicos => this.servicos = servicos);

    // this.registroForm = this.formBuilder.group({
    //   servico: ['', Validators.required],
    //   cep: ['', [
    //     Validators.required,
    //     Validators.pattern(/^\d{8}$/)
    //   ]],
    //   estado: ['', Validators.required],
    //   cidade: ['', Validators.required],
    //   bairro: ['', Validators.required],
    //   endereco: ['', Validators.required],
    //   quantidadeIncidentes: ['', Validators.required]
    // });
    this.registroForm = new FormGroup({
      servico: new FormControl('', Validators.required),
      cep: new FormControl('', {
        validators: [
          Validators.required,
          Validators.pattern(/^\d{8}$/)
        ],
        updateOn: 'blur'
      }),
      estado: new FormControl('', Validators.required),
      cidade: new FormControl('', Validators.required),
      bairro: new FormControl('', Validators.required),
      endereco: new FormControl('', Validators.required),
      quantidadeIncidentes: new FormControl('', Validators.required),
      anexo: new FormControl(''),
      descricao: new FormControl('', Validators.required)
    });

    // LER: https://angular.io/guide/reactive-forms#updating-parts-of-the-data-model
    // Tlvz precise para preencher o endereco a partir do CEP digitado.
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const servico = this.getValue('servico');
    const cep = this.getValue('cep');
    const estado = this.getValue('estado');
    const cidade = this.getValue('cidade');
    const bairro = this.getValue('bairro');
    const endereco = this.getValue('endereco');
    const quantidadeIncidentes = this.getValue('quantidadeIncidentes');
    const anexo = this.getValue('anexo');
    const descricao = this.getValue('descricao');

    console.log('servico', servico);
    console.log('cep', cep);
    console.log('estado', estado);
    console.log('cidade', cidade);
    console.log('bairro', bairro);
    console.log('endereco', endereco);
    console.log('quantidadeIncidentes', quantidadeIncidentes);
    console.log('descricao', descricao);

    const isValid = this.registroForm.valid;
    console.log('isValid', isValid);

    // TODO através de um service, chamar a API para salvar a nova ocorrência.
  }

  hasError(controlName: string, errorName: string): boolean {
    return this.registroForm.controls[controlName].touched &&
      this.registroForm.controls[controlName].hasError(errorName);
  }

  getValue(controlName: string) {
    // return this.registroForm.get(controlName)?.value;
    return this.registroForm.controls[controlName].value;
  }

}
