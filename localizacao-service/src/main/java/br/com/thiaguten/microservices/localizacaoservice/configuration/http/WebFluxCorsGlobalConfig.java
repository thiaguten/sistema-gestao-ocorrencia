// package br.com.thiaguten.microservices.localizacaoservice.configuration.http;

// import java.util.List;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.reactive.config.CorsRegistry;
// import org.springframework.web.reactive.config.EnableWebFlux;
// import org.springframework.web.reactive.config.WebFluxConfigurer;

// @Configuration
// @EnableWebFlux
// public class WebFluxCorsGlobalConfig implements WebFluxConfigurer {

//     @Override
//     public void addCorsMappings(CorsRegistry corsRegistry) {
//         var configuration = new CorsConfiguration();
//         configuration.applyPermitDefaultValues();
//         configuration.setAllowedMethods(List.of("*"));
//         corsRegistry
//                 .addMapping("/**")
//                 .combine(configuration);
//     }
// }
