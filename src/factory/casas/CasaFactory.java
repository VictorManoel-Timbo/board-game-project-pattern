package factory.casas;


import models.Casa;

public class CasaFactory {
    public static Casa criarCasa(int tipo, int numero) {
        switch (tipo) {
            case 1:
                return new CasaSurpresa(numero);
            case 2:
                return new CasaPrisao(numero);
            case 3:
                return new CasaSorte(numero);
            case 4:
                return new CasaAzar(numero);
            case 5:
                return new CasaReversa(numero);
            case 6:
                return new CasaJogaDeNovo(numero);
            case 7:
                return new CasaTroca(numero);
            case 8:
                return new CasaSimples(numero);
            default:
                throw new IllegalArgumentException("Tipo de casa não reconhecido.");
        }
    }
}
