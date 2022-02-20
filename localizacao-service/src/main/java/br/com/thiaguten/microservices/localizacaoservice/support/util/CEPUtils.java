package br.com.thiaguten.microservices.localizacaoservice.support.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.RegExUtils;

public final class CEPUtils {

    private CEPUtils() {
    }

    public static String apenasDigitos(String cep) {
        // remove qualquer coisa que não seja dígito da string CEP.
        return StringUtils.stripToNull(RegExUtils.removeAll(cep, "\\D"));
    }

}
