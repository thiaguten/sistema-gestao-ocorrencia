import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Ocorrencia } from 'src/app/ocorrencia/model/ocorrencia';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  username = '';
  //isAuthenticated?: boolean;
  isAuthenticated = true;
  displayedColumns = ['codigo', 'data'];
  ocorrencias$: Ocorrencia[] = [
    { codigo: '757e5f69-2327-4289-b6ae-6cbbb262bb91', data: '12/01/2022 16:10' },
    { codigo: '32028ebd-3ccb-43fb-8c09-3864f440a3a3', data: '05/02/2022 10:40' },
    { codigo: 'd03fa781-30aa-42a3-9b05-7788f3b60701', data: '11/12/2021 13:01' }
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
