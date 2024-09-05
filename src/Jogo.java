import Factories.CasaFactory;
import Factories.JogadorFactory;
import Models.Casa.Casa;
import Models.Jogador.Jogador;

import java.util.Scanner;

public class Jogo {
    private Tabuleiro tabuleiro;

    public Jogo() {
        tabuleiro = Tabuleiro.getInstancia();
    }

    public void configTabuleiro(int numCasas) {
        for (int i = 1; i <= numCasas; i++) {
            Casa casa = CasaFactory.criarCasa("simples", i);
            tabuleiro.adicionarCasa(casa);
        }
    }

    public void config(int numJogadores) {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < numJogadores; i++) {
            System.out.println("Digite o nome do jogador " + (i + 1) + ":");
            String nome = scanner.nextLine();

            System.out.println("Digite o tipo do jogador (sorte, azarado, normal):");
            String tipo = scanner.nextLine();

            Jogador jogador = JogadorFactory.criarJogador(tipo, nome);
            tabuleiro.adicionarJogador(jogador);
        }
    }

    public void printTabuleiro() {
        tabuleiro.printStatus();
    }

    public void start() {
        boolean jogoEmAndamento = true;

        while (jogoEmAndamento) {
            for (Jogador jogador : tabuleiro.getJogadores()) {
                System.out.println("É a vez de " + jogador.getNome() + " de jogar.");

                int movimento = jogador.jogarDados();
                System.out.println(jogador.getNome() + " tirou " + movimento + " nos dados.");

                jogador.mover(movimento);
                if (jogador.getPosicao() >= tabuleiro.getCasas().size()) {
                    System.out.println(jogador.getNome() + " venceu o jogo!");
                    jogoEmAndamento = false;
                    break;
                }

                Casa casa = tabuleiro.getCasas().get(jogador.getPosicao());
                casa.aplicarRegra(jogador);

                tabuleiro.printStatus();
            }
        }

        System.out.println("Jogo finalizado.");
    }
}

