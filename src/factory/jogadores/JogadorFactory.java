package factory.jogadores;

import factory.jogadores.JogadorAzarado;
import factory.jogadores.JogadorComum;
import models.Jogador;

public class JogadorFactory {
    public static Jogador criarJogador(int tipo) {
        switch (tipo) {
            case 1:
                return new JogadorComum();
            case 2:
                return new JogadorSortudo();
            case 3:
                return new JogadorAzarado();
            default:
                throw new IllegalArgumentException("Tipo de jogador não reconhecido.");
        }
    }
}
