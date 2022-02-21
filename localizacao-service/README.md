# SGO - Integração - Serviço de Localização

## Escopo
Serviço responsável por obter informações sobre uma determinada localização, mas especificamente sobre um endereço a partir de CEP fornecido se comunicando com um sistema externo.

## Pré-requisitos para execução do projeto
* RabbitMQ acessível
* Zipkin acessível
* Service Discovery (Eureka)

## Começando com o projeto
A ferramenta de build é o [Gradle](https://gradle.org/) execute os comandos abaixo para cada etapa:

1. Construção:
    - Windows: ```.\gradlew.bat clean build --stacktrace```
    - Unix: ```./gradlew clean build --stacktrace```
2. Execução:
    - Windows: ```.\gradlew.bat bootRun```
    - Unix: ```./gradlew bootRun```

3. Criação de [imagem OCI](https://github.com/opencontainers/image-spec) e instalação no Docker Daemon local via [Spring Boot Gradle Plugin](https://docs.spring.io/spring-boot/docs/2.6.3/gradle-plugin/reference/htmlsingle/#build-image);
    - Windows: ```.\gradlew.bat bootBuildImage```
    - Unix: ```./gradlew bootBuildImage```

## Endpoints do projeto
* ```/api/v1/endereco/cep/{cep:\\d{8}}```: Disponibiliza um endpoint para buscar informações sobre um endereço a partir de um CEP e retorna no padrão JSON.
* ```/api/v1/endereco/hateoas/cep/{cep:\\d{8}}```: Disponibiliza um endpoint para buscar informações sobre um endereço a partir de um CEP e retorna no padrão HAL+JSON.
* ```/actuator```: Disponibiliza recursos para monitorar, obter métricas, compreender o tráfego ou o estado do banco de dados, etc.

## Porta
* 8000
