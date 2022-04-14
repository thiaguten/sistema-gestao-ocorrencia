# SGO - Sistema de Ocorrência

## Escopo
Prova de Conceito (PoC) - TCC - PUC Minas - Arquitetura de Software Distribuído 2021.

Sistema para registro de ocorrência de serviços de manutenção em estruturas públicas que compõem uma infraestrutura urbuna.

## Pré-requisitos para execução do projeto
* [Docker](https://docs.docker.com/engine/)
* [Docker Compose](https://docs.docker.com/compose/)

## Começando com o projeto
O sistema está conteinerizado, onde seus módulos são imagens Docker. 

Docker Containers são unidades padrão de software que empacota todo ambiente, o código e todas as suas dependências, de forma autocontida. Afim de acabar com o famoso funciona na minha máquina.

Para executar todos eles de uma vez só e subir o sistema como um todo, a ferramenta Docker Compose deve ser utilizada através dos [comandos](https://docs.docker.com/compose/reference/) abaixo:

1. Iniciar o sistema pela primeira vez, após fazer o clone do projeto e via console/terminal estar na raiz do mesmo, execute o comando: 

```docker-compose up -d```

![Alt text](https://github.com/thiaguten/sistema-gestao-ocorrencia/blob/main/docs/assets/docker-compose-up.png "Comando docker-compose up")

Isso irá criar e iniciará os containers junto com os recursos de networks, volumes, etc. e via navegador web basta acessar o endereço: `http://localhost:4200/`

2. Parar o sistema, execute o comando:

```docker-compose stop```

![Alt text](https://github.com/thiaguten/sistema-gestao-ocorrencia/blob/main/docs/assets/docker-compose-stop.png "Comando docker-compose stop")

Isso irá parar os containers, mas sem removê-los! Preservando todas as informações criadas. Onde o sistema pode ser iniciados novamente desta vez apenas com o comando: 

```docker-compose start```

![Alt text](https://github.com/thiaguten/sistema-gestao-ocorrencia/blob/main/docs/assets/docker-compose-start.png "Comando docker-compose start")

2. Encerrar, removendo o containers previamente criados através do comando `up`, execute o comando: 

```docker-compose down```

![Alt text](https://github.com/thiaguten/sistema-gestao-ocorrencia/blob/main/docs/assets/docker-compose-down.png "Comando docker-compose down")

Isso irá parar e remover os containers e recursos de rede (networks) previamente criados. Neste caso o comando `up` deverá ser executado novamente para iniciar o sistema criando os containers e networks novamente.

3. Encerrar e resetar (factory reset/purge), execute o comando:

```docker-compose down --volumes --rmi all```

Isso irá para os containers e removê-los além das networks, dos volumes, e das imagens criadas e/ou baixadas pelo comando `up`.

## Telas

### Tela inicial

Tela inicial do sistema, onde é apresentada uma breve introdução sobre o mesmo.

![Alt text](https://github.com/thiaguten/sistema-gestao-ocorrencia/blob/main/docs/assets/tela-inicial.png "Tela inicial")

### Tela de cadastro

Tela de cadastro de usuário do sistema. Para que o usuário possa registrar uma ocorrência, primeiro têm que ser feito um cadastro inicial.

![Alt text](https://github.com/thiaguten/sistema-gestao-ocorrencia/blob/main/docs/assets/tela-cadastro.png "Tela de cadastro")

### Tela de login

Tela de autenticação ao sistema. O sistema utiliza o conceito de SSO (Single-Sign-On) e para tal usa o servidor de autenticação Keycloak para gerenciamento de acesso e identificação do usuário.

![Alt text](https://github.com/thiaguten/sistema-gestao-ocorrencia/blob/main/docs/assets/tela-login.png "Tela de login")

### Tela de home

Tela inicial após autenticação no sistema. Apresenta as funcionalidade no qual o usuário logado tem permissão de acesso e para o perfil de usuário público comum, apresenta uma tabela listando as últimas ocorrências registradas pelo mesmo.

![Alt text](https://github.com/thiaguten/sistema-gestao-ocorrencia/blob/main/docs/assets/tela-home.png "Tela de home")

### Tela de registro de ocorrência

Tela de registro de ocorrência. Permite ao usuário registrar uma ocorrência detalhando um serviço específico, endereço entre outras informações que possam ser pertinentes.

![Alt text](https://github.com/thiaguten/sistema-gestao-ocorrencia/blob/main/docs/assets/tela-registro-ocorrencia.png "Tela de registro de ocorrência")

## Bando de dados

### MySQL

O MySQL é o servidor de bando de dados usado pelo sistema para manter as informações oriundas do processo de gestão de ocorrências e de regras de perfil de acesso de usuários. 

São criados 2 (dois) banco de dados: `db_sgo` e `db_keycloak` acessíveis através das URLs JDBC: `jdbc:mysql://localhost/db_sgo` e `jdbc:mysql://localhost/db_keycloak` respectivamente. Sendo para ambos o usuário `user_sgo` e senha `Sg0p@SsWd`

### MongoDB

O MongoDB é o banco de dados usado para cadastro e busca de informações relacionadas à localização da ocorrência, como informações de endereço e CEP. E no futuro poderá auxiliar em funcionalidade de Geo Localização.

É criado 1 (um) banco de dados: `db_sgo` acessível através da URI: `mongodb://user_sgo:Sg0p%40SsWd@localhost:27017/db_sgo` contendo apenas uma coleção enderecos.
