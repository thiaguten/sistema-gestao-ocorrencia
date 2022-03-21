package br.com.thiaguten.microservices.ocorrenciaservice.configuration;

import static br.com.thiaguten.microservices.ocorrenciaservice.support.security.RolesAllowedConstants.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.MappedJwtClaimSetConverter;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Configuration
@EnableWebSecurity
public class SecurityResourceServerConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    String jwkSetUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        
        // Excluir as requisições preflight da autorização.
        http.cors().and()
                .authorizeHttpRequests(authorize -> authorize
                    .antMatchers(HttpMethod.POST, "/api/v1/usuarios").permitAll()

                    // Aplica uma restrição mais global, através de roles focadas no realm_access.

                    .mvcMatchers("/api/v1/servicos/*").hasRole(REALM_ROLE_SGO_USER)
                    .mvcMatchers("/api/v1/ocorrencias/*").hasRole(REALM_ROLE_SGO_USER)

                    // Restrição mais granular através de roles focadas mais no resource_access.

                    // .mvcMatchers(HttpMethod.GET, "/api/v1/servicos/**")
                    //     .hasAnyRole(REALM_ROLE_SGO_USER, CLIENT_ROLE_SERVICOS_READ)
                    // .mvcMatchers(HttpMethod.GET, "/api/v1/ocorrencias/**")
                    //     .hasAnyRole(REALM_ROLE_SGO_USER, CLIENT_ROLE_OCORRENCIAS_READ)
                    // .mvcMatchers(HttpMethod.POST, "/api/v1/ocorrencias/**")
                    //     .hasAnyRole(REALM_ROLE_SGO_USER, CLIENT_ROLE_OCORRENCIAS_WRITE)
                    
                    // Any other request requires the user to be authenticated.
                    .anyRequest().authenticated()
                )
                // .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
                .oauth2ResourceServer(oauth2 -> oauth2
                    // Indica o tipo de token suportado por essa aplicação (JWT) para validação
                    // através do provedor OpenID configurado. Além disso, configura uma
                    // customização de conversão do token JWT para obter as ROLES a partir de um
                    // claim específico.
                    .jwt(jwt -> jwt
                        .jwtAuthenticationConverter(jwtAuthenticationConverter())
                    )
                );

        // Desativar o CSRF para prevenir conflitos com o serviço de CSRF.
        http.csrf().disable();                

        return http.build();
        // @formatter:on
    }

    // @formatter:off
    @Bean
    JwtDecoder jwtDecoder() {
    // JwtDecoder jwtDecoder(OAuth2ResourceServerProperties properties) {
        // var issuerUri = properties.getJwt().getIssuerUri();
        var issuerUri = this.jwkSetUri;
        var jwtDecoder = NimbusJwtDecoder.withJwkSetUri(issuerUri).build();
        // jwtDecoder.setClaimSetConverter(new UsernameSubClaimAdapter());
        return jwtDecoder;
    }
    // @formatter:on

    private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
        // @formatter:off
        // This converter (JwtGrantedAuthoritiesConverter) can not handle map/jsonObject
        // claim type.
        // var authoritiesConverter = 
        //     new org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter();
        // authoritiesConverter.setAuthorityPrefix("ROLE_");
        // @formatter:on

        var authoritiesConverter = new CustomJwtGrantedAuthoritiesConverter();
        authoritiesConverter.setAuthoritiesClaimName("realm_access");
        // authoritiesConverter.setAuthoritiesClaimName("resource_access");

        var jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);
        return jwtAuthenticationConverter;
    }

    /**
     * Use preferred_username from claims as authentication name, instead of UUID
     * subject.
     */
    public static class UsernameSubClaimAdapter implements Converter<Map<String, Object>, Map<String, Object>> {
        private final MappedJwtClaimSetConverter delegate = MappedJwtClaimSetConverter
                .withDefaults(Collections.emptyMap());

        @Override
        public Map<String, Object> convert(Map<String, Object> claims) {
            Map<String, Object> convertedClaims = this.delegate.convert(claims);
            String username = (String) convertedClaims.get("preferred_username");
            convertedClaims.put(JwtClaimNames.SUB, username);
            return convertedClaims;
        }
    }

    /**
     * Custom JWT authorities/RBAC (Role Based Access Control) converter to handle
     * map/jsonObject claim type.
     * 
     * <p>
     * For example, it converts the Keycloak 'realm_access' claims (JWT claim name
     * that has a list of roles) to granted authorities for use in access decisions.
     * 
     * <pre>
     * {@code
     *   ...
     *   "realm_access": {
     *     "roles": [
     *         "default-roles-sgo",
     *         "offline_access",
     *         "uma_authorization",
     *         "sgo-user"
     *     ]
     *   },
     *   "resource_access": {
     *     "sgo-ocorrencia-service": {
     *       "roles": [
     *         "ocorrencias_read",
     *         "ocorrencias_write",
     *         "servicos_read",
     *         "servicos_write"
     *       ]
     *     }
     *   }
     *   ...
     * }
     * </pre>
     */
    @SuppressWarnings({ "unchecked", "unused" })
    public static class CustomJwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

        private static final Logger LOGGER = LoggerFactory.getLogger(CustomJwtGrantedAuthoritiesConverter.class);

        private static final String DEFAULT_AUTHORITY_PREFIX = "ROLE_";
        private static final Collection<String> WELL_KNOWN_AUTHORITIES_CLAIM_NAMES = List.of("roles", "authorities");

        private String authoritiesClaimName;
        private String authorityPrefix = DEFAULT_AUTHORITY_PREFIX;
        private GrantedAuthoritiesMapper authorityMapper = new SimpleAuthorityMapper();

        @Override
        public Collection<GrantedAuthority> convert(Jwt jwt) {
            Collection<String> authorities = getAuthorities(jwt);

            // // @formatter:off
            // return authorities.stream()
            //         .map(authority -> this.authorityPrefix + authority)
            //         .map(SimpleGrantedAuthority::new)
            //         .collect(Collectors.toList());
            // // @formatter:on

            List<SimpleGrantedAuthority> grantedAuthorities = authorities.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            return (Collection<GrantedAuthority>) this.authorityMapper.mapAuthorities(grantedAuthorities);
        }

        public void setAuthorityMapper(GrantedAuthoritiesMapper authorityMapper) {
            Assert.notNull(authorityMapper, "authorityMapper cannot be null");
            this.authorityMapper = authorityMapper;
        }

        // deprecated in favor of setAuthorityMapper method
        @Deprecated
        public void setAuthorityPrefix(String authorityPrefix) {
            Assert.notNull(authorityPrefix, "authorityPrefix cannot be null");
            this.authorityPrefix = authorityPrefix;
        }

        public void setAuthoritiesClaimName(String authoritiesClaimName) {
            Assert.hasText(authoritiesClaimName, "authoritiesClaimName cannot be empty");
            this.authoritiesClaimName = authoritiesClaimName;
        }

        private String getAuthoritiesClaimName(Jwt jwt) {
            if (authoritiesClaimName != null) {
                return authoritiesClaimName;
            }
            for (String claimName : WELL_KNOWN_AUTHORITIES_CLAIM_NAMES) {
                if (jwt.hasClaim(claimName)) {
                    return claimName;
                }
            }
            return null;
        }

        private Collection<String> getAuthorities(Jwt jwt) {
            String claimName = getAuthoritiesClaimName(jwt);

            if (claimName == null) {
                LOGGER.trace("Returning no authorities since could not find any claims that might contain roles");
                return Collections.emptyList();
            }

            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace(String.format("Looking for roles in claim %s", claimName));
            }

            return parse(jwt.getClaim(claimName));
        }

        private Collection<String> parse(Object authorities) {
            if (authorities != null) {
                if (authorities instanceof Map) {
                    Map<String, Object> authoritiesMap = castAuthoritiesToMap(authorities);
                    for (Object value : authoritiesMap.values()) {
                        return parse(value);
                    }
                }
                if (authorities instanceof String) {
                    if (StringUtils.hasText((String) authorities)) {
                        return Arrays.asList(((String) authorities).split(" "));
                    }
                    return Collections.emptyList();
                }
                if (authorities instanceof Collection) {
                    return castAuthoritiesToCollection(authorities);
                }
            }
            return Collections.emptyList();
        }

        private Collection<String> castAuthoritiesToCollection(Object authorities) {
            return (Collection<String>) authorities;
        }

        private Map<String, Object> castAuthoritiesToMap(Object authorities) {
            return (Map<String, Object>) authorities;
        }
    }

}
