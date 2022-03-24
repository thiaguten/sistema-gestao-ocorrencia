export interface Usuario {
  id?: number;
  idpId?: string;
  cpf: string;
  email: string;
  nomeUsuario: string;
  primeiroNome: string;
  ultimoNome: string;
  senha: string;
  notificacaoEmailAtivo?: boolean;
}
