// Múltiplos contextos de API para um único endpoint alvo.

const PROXY_CONFIG = [
  {
    context: [
      "/api/v1/ocorrencias",
      "/api/v1/usuarios",
      "/api/v1/servicos"
    ],
    target: "http://localhost:8200",
    secure: false,
    logLevel: 'debug'
  },
  {
    context: [
      "/api/v1/endereco"
    ],
    target: "http://localhost:8000",
    secure: false,
    logLevel: 'debug'
  }
]

module.exports = PROXY_CONFIG;
