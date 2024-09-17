package factory.casas;

import models.Casa;
import models.Jogador;

import java.util.ArrayList;
import java.util.Comparator;

public class CasaReversa extends Casa {
    public CasaReversa(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(ArrayList<Jogador> listaDeJogadores, Jogador jogador) {
        System.out.println(("Casa Reversa: Troca de lugar com o jogador mais atrás no jogo."));
        ArrayList<Jogador> playersCopy = new ArrayList<>(listaDeJogadores);

        playersCopy.sort(Comparator.comparingInt(Jogador::getPosicao));

        Jogador firstPlayer = playersCopy.get(0);
        int aux = firstPlayer.getPosicao();

        System.out.println("Jogador " + jogador.getCor() + " trocou de posição com o jogador " + firstPlayer.getCor());

        listaDeJogadores.get(listaDeJogadores.indexOf(firstPlayer)).setPosicao(jogador.getPosicao());
        jogador.setPosicao(aux);
    }
}