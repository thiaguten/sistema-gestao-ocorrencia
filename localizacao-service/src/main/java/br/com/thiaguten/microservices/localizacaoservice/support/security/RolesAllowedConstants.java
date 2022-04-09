package br.com.thiaguten.microservices.localizacaoservice.support.security;

public final class RolesAllowedConstants {

    public static final String REALM_ROLE_SGO_USER = "sgo-user";
    public static final String REALM_ROLE_SGO_ADMIN = "sgo-admin";
    public static final String CLIENT_ROLE_ENDERECOS_READ = "enderecos_read";
    public static final String CLIENT_ROLE_ENDERECOS_WRITE = "enderecos_write";

    private RolesAllowedConstants() {
    }
}
