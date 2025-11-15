package com.logologo.api.utils;

public class CartaoUtils {

    public static String mascararNumero(String numero) {
        if (numero == null || numero.length() < 4) {
            return "****";
        }

        int tamanho = numero.length();
        int visiveis = 4;
        int ocultos = tamanho - visiveis;

        return "*".repeat(ocultos) + numero.substring(tamanho - visiveis);
    }
}
