# SGO - Serviço de Localização

## Escopo
Microserviço desenvolvido utilizando o framework SpringBoot, expõe endpoints RESTful acessíveis a partir de solicitações HTTP para um IP e porta específico juntamente com um JSON Web Token no cabeçalho de autenticação para possibilitar busca de informações de endereço relacionadas à um CEP. Esse serviço, através de um cliente HTTP, se integra com um sistema externo para realizar solicitações de busca, obtendo como retorno o endereço em formato JSON que por sua vez é cacheado no banco de dados MongoDB para propiciar certa confiabilidade de resposta caso a integração externa tenha algum problema.

## Pré-requisitos para execução do projeto
* RabbitMQ
* Zipkin
* Service Discovery (Eureka)
* MongoDB
* Keycloak

## Começando com o projeto
A ferramenta de build é o [Gradle](https://gradle.org/) execute os comandos abaixo para cada etapa:

1. Construção:
    - Windows: ```.\gradlew.bat clean build --stacktrace```
    - Unix: ```./gradlew clean build --stacktrace```
2. Execução:
    - Windows: ```.\gradlew.bat bootRun```
    - Unix: ```./gradlew bootRun```

3. Conteinerização: Utilização da [especificação](https://github.com/buildpacks/spec/blob/main/buildpack.md) Cloud Native Buildpacks ([CNB](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#container-images.buildpacks)) para transformar o código fonte da aplicação em uma [imagem OCI](https://github.com/opencontainers/image-spec) e instalação no Docker Daemon local via [Spring Boot Gradle Plugin](https://docs.spring.io/spring-boot/docs/2.6.3/gradle-plugin/reference/htmlsingle/#build-image);
    - Windows: ```.\gradlew.bat bootBuildImage```
    - Unix: ```./gradlew bootBuildImage```

## Endpoints do projeto
* ```/api/v1/enderecos/cep/{cep:\\d{8}}```: Disponibiliza um endpoint para buscar informações sobre um endereço a partir de um CEP e retorna no padrão JSON.
* ```/api/v1/enderecos/hateoas/cep/{cep:\\d{8}}```: Disponibiliza um endpoint para buscar informações sobre um endereço a partir de um CEP e retorna no padrão HAL+JSON.
* ```/actuator```: Disponibiliza recursos para monitorar, obter métricas, compreender o tráfego ou o estado do banco de dados, etc.

## Porta
* 8000
