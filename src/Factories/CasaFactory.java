package Factories;

import Models.Casa.Casa;
import Models.Casa.CasaSimples;

public class CasaFactory {
    public static Casa criarCasa(String tipo, int numero) {
        switch (tipo) {
            case "simples":
                return new CasaSimples(numero);
            //case "prisao":
            //    return new CasaPrisao(numero);
            // Outras casas
            default:
                return new CasaSimples(numero);
        }
    }
}