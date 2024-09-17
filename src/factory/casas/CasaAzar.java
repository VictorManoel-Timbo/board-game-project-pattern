package factory.casas;

import factory.jogadores.JogadorSortudo;
import models.Casa;
import models.Jogador;

import java.util.ArrayList;

public class CasaAzar extends Casa {
    public CasaAzar(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(ArrayList<Jogador> listaDeJogadores, Jogador jogador) {
        if (!(jogador instanceof JogadorSortudo)) {
            System.out.println("Casa Azar: O jogador perde 3 moedas!");
            if (jogador.getMoedas() > 3) {
                jogador.setMoedas(jogador.getMoedas() - 3);
            } else {
                System.out.println("O jogador tem 3 moedas ou menos, irá perder todas as moedas");
                jogador.setMoedas(0);
            }
        } else {
            System.out.println("Casa Azar: O jogador sortudo escapa da praga!");
        }
    }
}
