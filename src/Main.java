import decorator.JogadorComBone;
import decorator.JogadorComMoletom;
import decorator.JogadorComOculosEscuros;
import factory.casas.CasaSimples;
import factory.jogadores.JogadorComum;
import models.Casa;
import models.Jogador;
import models.Jogar;

public class Main {

    public static void main(String[] args) {
        Jogar jogo = new Jogar();
        jogo.play();
    }

}