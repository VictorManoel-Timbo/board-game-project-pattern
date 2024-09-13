package models;

import java.util.ArrayList;
import java.util.HashMap;

import static models.Jogo.RESET;

public class Tabuleiro {
    private static Tabuleiro instancia;
    private ArrayList<Jogador> listaDeJogadores;
    private ArrayList<Casa> listaDeCasas;
    private int jogadorAtual = 0;

    private Tabuleiro(ArrayList<Jogador> listaDeJogadores, ArrayList<Casa> listaDeCasas) {
        this.listaDeJogadores = listaDeJogadores;
        this.listaDeCasas = listaDeCasas;
    }

    public static Tabuleiro getInstancia(ArrayList<Jogador> listaDeJogadores, ArrayList<Casa> listaDeCasas) {
        if (instancia == null) {
            instancia = new Tabuleiro(listaDeJogadores, listaDeCasas);
        }
        return instancia;
    }

    public void mostrarEstado(HashMap<String, String> nomeDasCores) {
        for (Jogador jogador : listaDeJogadores) {
            String nomeCor = nomeDasCores.get(jogador.getCor());
            System.out.println(jogador.getCor() + "Jogador " + nomeCor + " na casa " + jogador.getPosicao() + " com " + jogador.getMoedas() + " moedas e equipamentos: " + jogador.getEquipamentos() + RESET);
        }
        System.out.println("É a vez do jogador: " + listaDeJogadores.get(jogadorAtual).getCor());
    }

    public void jogarRodada(HashMap<String, String> nomeDasCores) {
        Jogador jogador = listaDeJogadores.get(jogadorAtual);
        int[] dados = jogador.jogarDados();
        int somaDados = dados[0] + dados[1];
        String nomeCor = nomeDasCores.get(jogador.getCor());
        System.out.println(jogador.getCor() + "Jogador " + nomeCor + " jogou os dados: " + dados[0] + " e " + dados[1] + ". Soma: " + somaDados + RESET);

        int novaPosicao = jogador.getPosicao() + somaDados;
        jogador.setPosicao(novaPosicao);
        System.out.println("Jogador " + jogador.getCor() + " moveu para a casa " + novaPosicao);

        verificarCasaEspecial(jogador);

        jogadorAtual = (jogadorAtual + 1) % listaDeJogadores.size();
    }

    private void verificarCasaEspecial(Jogador jogador) {
        for (Casa casa : listaDeCasas) {
            if (jogador.getPosicao() == casa.getNumero()) {
                casa.aplicarRegra(jogador);
                break;
            }
        }
    }
}
