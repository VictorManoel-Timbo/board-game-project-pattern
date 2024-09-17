package factory.casas;

import factory.jogadores.JogadorAzarado;
import factory.jogadores.JogadorComum;
import factory.jogadores.JogadorSortudo;
import models.Casa;
import models.Jogador;

import java.util.ArrayList;
import java.util.Random;

public class CasaSurpresa extends Casa {

    public CasaSurpresa(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(ArrayList<Jogador> listaDeJogadores, Jogador jogador) {
        System.out.println("Casa Surpresa: Você ganha um novo tipo de jogador");
        Random random = new Random();
        Jogador novoJogador = jogador;
        int novoTipo = random.nextInt(3) + 1;
        int indexJogador = listaDeJogadores.indexOf(jogador);

        switch (novoTipo) {
            case 1 -> {
                System.out.println("Você tirou a carta Comum: agora você é um Jogador Comum.");
                novoJogador = new JogadorComum(jogador);
            }
            case 2 -> {
                System.out.println("Você tirou a carta Sorte: agora você é um Jogador Sortudo.");
                novoJogador = new JogadorSortudo(jogador);
            }
            case 3 -> {
                System.out.println("Você tirou a carta Azar: agora você é um Jogador Azarado.");
                novoJogador = new JogadorAzarado(jogador);
            }
        }

        listaDeJogadores.set(indexJogador, novoJogador);
    }
}