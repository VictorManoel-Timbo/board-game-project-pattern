package factory.casas;


import models.Casa;

public class CasaFactory {
    public static Casa criarCasa(int tipo, int numero) {
        return switch (tipo) {
            case 1 -> new CasaSurpresa(numero);
            case 2 -> new CasaPrisao(numero);
            case 3 -> new CasaSorte(numero);
            case 4 -> new CasaAzar(numero);
            case 5 -> new CasaReversa(numero);
            case 6 -> new CasaJogaDeNovo(numero);
            case 7 -> new CasaTroca(numero);
            case 8 -> new CasaSimples(numero);
            default -> throw new IllegalArgumentException("Tipo de casa não reconhecido.");
        };
    }
}
