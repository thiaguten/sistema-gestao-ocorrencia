# SGO - Serviço de Ocorrência

## Escopo
Microserviço desenvolvido utilizando o framework SpringBoot, expõe endpoints RESTful acessíveis a partir de solicitações HTTP para um IP e porta específico juntamente com um JSON Web Token no cabeçalho de autenticação para possibilitar a manipulação de dados que compõem uma ocorrência e através do processamento negocial manter as informações no banco de dados MySQL.

## Pré-requisitos para execução do projeto
* RabbitMQ
* Zipkin
* Service Discovery (Eureka)
* MySQL
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
* ```/api/v1/ocorrencias/*```: Disponibiliza endpoints RESTful para manter ocorrências e retorna no padrão HAL+JSON.
* ```/api/v1/servicos/*```: Disponibiliza endpoints RESTful para manter serviços e retorna no padrão HAL+JSON.
* ```/api/v1/usuarios/*```: Disponibiliza endpoints RESTful para manter usuários e retorna no padrão HAL+JSON.
* ```/actuator```: Disponibiliza recursos para monitorar, obter métricas, compreender o tráfego ou o estado do banco de dados, etc.

## Porta
* 8200
