# SGO - Serviço de Registro e Descoberta - Service Discovery (Eureka)

## Escopo
Microserviço desenvolvido utilizando o framework SpringBoot, viabilizado através do componente Netflix [Eureka](https://github.com/Netflix/eureka/wiki/Eureka-REST-operations), mantém um cadastro das instâncias e localidades dos serviços facilitando a descoberta e solicitação sem a necessidade de que um cliente e/ou API gateway saiba exatamente o IP e porta de um determinado microserviço para eventuais solicitações. As instâncias de serviço são registradas durante inicialização e desregistadas no encerramento.

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

## Porta
* 8761
