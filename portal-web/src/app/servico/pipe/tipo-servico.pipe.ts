import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'tipoServico'
})
export class TipoServicoPipe implements PipeTransform {

  transform(servicoId: number): string {
    switch (servicoId) {
      case 1:
        return 'Tapa Buraco';
      case 2:
        return 'Caça Vazamento';
      default:
        return `${servicoId} - ???`;
    }
  }

}
