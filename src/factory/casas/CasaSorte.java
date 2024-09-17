package factory.casas;

import factory.jogadores.JogadorAzarado;
import models.Casa;
import models.Jogador;

import java.util.ArrayList;

public class CasaSorte extends Casa {
    public CasaSorte(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(ArrayList<Jogador> listaDeJogadores, Jogador jogador) {
        if (!(jogador instanceof JogadorAzarado)) {
            System.out.println("Casa Sorte: O jogador avança 3 casas!");
            jogador.setPosicao(jogador.getPosicao()+3);
        } else {
            System.out.println("Casa Sorte: O jogador azarado não sai do lugar");
        }
    }
}