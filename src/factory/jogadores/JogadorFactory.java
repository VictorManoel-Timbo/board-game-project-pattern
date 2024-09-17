package factory.jogadores;

import models.Jogador;

public class JogadorFactory {
    public static Jogador criarJogador(int tipo) {
        return switch (tipo) {
            case 1 -> new JogadorComum();
            case 2 -> new JogadorAzarado();
            case 3 -> new JogadorSortudo();
            default -> throw new IllegalArgumentException("Tipo de jogador não reconhecido.");
        };
    }
}
