// package br.com.thiaguten.microservices.localizacaoservice.configuration.http;

// import java.util.List;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.EnableWebMvc;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// @EnableWebMvc
// public class WebCorsGlobalConfig implements WebMvcConfigurer {

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
