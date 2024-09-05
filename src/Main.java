import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o número de casas do tabuleiro:");
        int numCasas = scanner.nextInt();
        jogo.configTabuleiro(numCasas);

        System.out.println("Digite o número de jogadores:");
        int numJogadores = scanner.nextInt();
        jogo.config(numJogadores);

        jogo.printTabuleiro();
        jogo.start();
    }
}