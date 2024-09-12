package factory.casas;


import models.Casa;

public class CasaFactory {
    public static Casa criarCasa(int tipo) {
        switch (tipo) {
            case 1:
                return new CasaSurpresa();
            case 2:
                return new CasaPrisao();
            case 3:
                return new CasaSorte();
            case 4:
                return new CasaAzar();
            case 5:
                return new CasaReversa();
            case 6:
                return new CasaJogaDeNovo();
            case 7:
                return new CasaTroca();
            case 8:
                return new CasaSimples();
            default:
                throw new IllegalArgumentException("Tipo de casa não reconhecido.");
        }
    }
}
