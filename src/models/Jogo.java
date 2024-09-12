package models;

import factory.casas.CasaFactory;
import factory.jogadores.JogadorFactory;

import java.util.ArrayList;
import java.util.Scanner;

public class Jogo {
    Scanner input = new Scanner(System.in);
    ArrayList<Jogador> listaDeJogadores = new ArrayList<Jogador>();
    ArrayList<Casa> listaDeCasas = new ArrayList<Casa>();
    Tabuleiro tabuleiro;
    private void config(int numJogadores){
        for (int i=0; i<numJogadores; i++) {
            System.out.println("""
                    Selecione um tipo de jogador:
                    1 - Jogador Comum
                    2 - Jogador Azarado
                    3 - Jogador Sortudo""");
            int tipo = input.nextInt();
            try {
                listaDeJogadores.add(JogadorFactory.criarJogador(tipo));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private void configTabuleiro(int numCasas){
        for (int i=0; i<numCasas; i++) {
            System.out.println("""
                    Selecione um tipo de jogador:
                    1 - Casa Surpresa
                    2 - Casa Prisão
                    3 - Casa Sorte
                    4 - Casa Azar
                    5 - Casa Reversa
                    6 - Casa Jogar de Novo
                    7 - Casa Troca""");
            int tipo = input.nextInt();
            try {
                Casa casaEspecial = CasaFactory.criarCasa(tipo);
                System.out.println("Digite o número da casa: ");
                int numero = input.nextInt();
                casaEspecial.setNumero(numero);
                listaDeCasas.add(casaEspecial);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void play(){
    }
}
