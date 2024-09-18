
import models.Jogo;

import java.util.InputMismatchException;
import java.util.Scanner;

import static models.Tabuleiro.LIMITE_CASAS;
import static models.Tabuleiro.LIMITE_JOGADORES;


public class Main {

    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        int numJogadores;
        int numCasas;
        Scanner input = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Digite o número de casas do tabuleiro (máximo de " + LIMITE_CASAS + "):");
                numCasas = input.nextInt();
                if (numCasas < 10 || numCasas > LIMITE_CASAS) {
                    throw new IllegalArgumentException("Número de casas deve ser entre 10 e " + LIMITE_CASAS);
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                input.next();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.println("Digite o número de jogadores (máximo de " + LIMITE_JOGADORES + "):");
                numJogadores = input.nextInt();
                if (numJogadores <= 1 || numJogadores > LIMITE_JOGADORES) {
                    throw new IllegalArgumentException("Número de jogadores deve ser entre 2 e " + LIMITE_JOGADORES);
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                input.next();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        jogo.configTabuleiro(numCasas);
        jogo.config(numJogadores);
        jogo.printTabuleiro();
        jogo.start();
    }
}