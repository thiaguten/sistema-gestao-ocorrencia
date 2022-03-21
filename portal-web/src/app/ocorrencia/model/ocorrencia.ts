export interface Ocorrencia {
  id?: number;
  codigo?: string;
  descricao: string;
  cep: string;
  logradouro: string;
  bairro: string;
  localidade: string;
  uf: string;
  dataCriacao?: string;
  dataModificacao?: string;
  situacao?: string;
  servicoId: number;
}
