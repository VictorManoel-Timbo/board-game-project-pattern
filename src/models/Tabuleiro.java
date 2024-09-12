package models;

import java.util.ArrayList;

public class Tabuleiro {
    private static Tabuleiro instancia;
    private Tabuleiro(ArrayList<Jogador> listaDeJogadores, ArrayList<Casa> listaDeCasas){
        for (Jogador jogador : listaDeJogadores) {
            for (Casa casa : listaDeCasas) {
                if (jogador.getPosicao() == casa.getNumero()){
                    casa.aplicarRegra(jogador);
                }
            }
        }
    }
    public static Tabuleiro getInstancia(ArrayList<Jogador> listaDeJogadores, ArrayList<Casa> listaDeCasas) {
        if (instancia == null) {
            instancia = new Tabuleiro(listaDeJogadores, listaDeCasas);
        }
        return instancia;
    }
}
