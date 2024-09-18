package factory.casas;

import models.Casa;
import models.Jogador;

import java.util.ArrayList;
import java.util.Comparator;

import static models.Cores.nomeDasCores;

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
        if (jogador.getCor().equals(firstPlayer.getCor())) System.out.println("Jogador não troca de posição, pois é o ultimo colocado\n");
        else System.out.println("Jogador " + nomeDasCores.get(jogador.getCor()) + " trocou de posição com o jogador " + nomeDasCores.get(firstPlayer.getCor()) + "\n");

        listaDeJogadores.get(listaDeJogadores.indexOf(firstPlayer)).setPosicao(jogador.getPosicao());
        jogador.setPosicao(aux);
    }
}