package models;

import factory.casas.CasaFactory;
import factory.casas.CasaSimples;
import factory.jogadores.JogadorFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Jogo {
    Scanner input = new Scanner(System.in);
    ArrayList<Jogador> listaDeJogadores = new ArrayList<>();
    ArrayList<Casa> listaDeCasas = new ArrayList<>();
    Tabuleiro tabuleiro;
    int totalJogadas = 0;
    int ultimaCasa;
    boolean modoDebug = false;
    public static final int LIMITE_JOGADORES = 6;
    public static final int LIMITE_CASAS = 40;
    public static final double PERCENTUAL_MAX_CASAS_ESPECIAIS = 0.5;

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CIANO = "\u001B[36m";

    ArrayList<String> listaDeCores = new ArrayList<>(Arrays.asList(RED, GREEN, YELLOW, BLUE, PURPLE, CIANO));
    HashMap<String, String> nomeDasCores = new HashMap<>() {{
        put(RED, "vermelho");
        put(GREEN, "verde");
        put(YELLOW, "amarelo");
        put(BLUE, "azul");
        put(PURPLE, "roxo");
        put(CIANO, "ciano");
    }};

    private void configJogadores() {
        int numJogadores;
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

        int primeiroTipo = -1;

        for (int i = 0; i < numJogadores; i++) {
            while (true) {
                try {
                    System.out.println("""
                    Selecione um tipo de jogador:
                    1 - Jogador Comum
                    2 - Jogador Azarado
                    3 - Jogador Sortudo""");
                    int tipo = input.nextInt();

                    if (i == 0) {
                        primeiroTipo = tipo;
                    }

                    else if (tipo == primeiroTipo && i == 1) {
                        System.out.println("Por favor, escolha um tipo de jogador diferente do primeiro.");
                        continue;
                    }

                    Jogador jogador = JogadorFactory.criarJogador(tipo);

                    String cor = listaDeCores.get(i % listaDeCores.size());
                    jogador.setCor(cor);

                    jogador.setPosicao(0);
                    listaDeJogadores.add(jogador);

                    String nomeCor = nomeDasCores.get(cor);
                    System.out.println(cor + "Jogador " + (i + 1) + " adicionado com a cor " + nomeCor + "!" + RESET);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                    input.next();
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }


    private void configTabuleiro() {
        int numCasas;
        while (true) {
            try {
                System.out.println("Digite o número de casas do tabuleiro (máximo de " + LIMITE_CASAS + "):");
                numCasas = input.nextInt();
                if (numCasas <= 0 || numCasas > LIMITE_CASAS) {
                    throw new IllegalArgumentException("Número de casas deve ser entre 1 e " + LIMITE_CASAS);
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                input.next();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        ultimaCasa = numCasas;
        int numCasasEspeciais;
        while (true) {
            try {
                System.out.println("Quantas casas especiais deseja adicionar (máximo de " + (int) (numCasas * PERCENTUAL_MAX_CASAS_ESPECIAIS) + ")?");
                numCasasEspeciais = input.nextInt();
                if (numCasasEspeciais < 0 || numCasasEspeciais > numCasas * PERCENTUAL_MAX_CASAS_ESPECIAIS) {
                    throw new IllegalArgumentException("Número de casas especiais não pode exceder " + (int) (numCasas * PERCENTUAL_MAX_CASAS_ESPECIAIS));
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                input.next();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        for (int i = 0; i < numCasasEspeciais; i++) {
            while (true) {
                try {
                    System.out.println("""
                        Selecione um tipo de casa especial:
                        1 - Casa Surpresa
                        2 - Casa Prisão
                        3 - Casa Sorte
                        4 - Casa Azar
                        5 - Casa Reversa
                        6 - Casa Jogar de Novo
                        7 - Casa Troca""");
                    int tipo = input.nextInt();
                    if (tipo < 1 || tipo > 7) {
                        throw new InputMismatchException();
                    }
                    System.out.println("Digite o número da casa especial (de 1 a " + numCasas + "): ");
                    int numeroCasaEspecial = input.nextInt();
                    if (numeroCasaEspecial <= 0 || numeroCasaEspecial > numCasas) {
                        throw new InputMismatchException();
                    }

                    boolean casaJaExiste = listaDeCasas.stream().anyMatch(casa -> casa.getNumero() == numeroCasaEspecial);
                    if (casaJaExiste) {
                        System.out.println("Essa casa especial já existe. Escolha outro número.");
                        continue;
                    }

                    Casa casaEspecial = CasaFactory.criarCasa(tipo, numeroCasaEspecial);
                    listaDeCasas.add(casaEspecial);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor, insira um número válido.");
                    input.nextLine();
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        for (int i = 1; i <= numCasas; i++) {
            int finalI = i;
            boolean casaEspecialExiste = listaDeCasas.stream().anyMatch(casa -> casa.getNumero() == finalI);
            if (!casaEspecialExiste) {
                listaDeCasas.add(new CasaSimples(i));
            }
        }
    }

    public void start() {
        System.out.println("Bem-vindo ao jogo!");

        try {
            configJogadores();
            configTabuleiro();

            System.out.println("Deseja ativar o modo debug? (1 para sim, 2 para não)");
            int escolhaDebug = input.nextInt();
            if (escolhaDebug == 1) {
                modoDebug = true;
                System.out.println("Modo debug ativado.");
            }

            tabuleiro = Tabuleiro.getInstancia(listaDeJogadores, listaDeCasas);
            tabuleiro.setQtdCasas(ultimaCasa);
            boolean jogoTerminado = false;

            while (!jogoTerminado) {
                for (Jogador jogador : listaDeJogadores) {
                    tabuleiro.mostrarEstado(jogador);
                    tabuleiro.jogarRodada(jogador, modoDebug);

                    if (jogador.getPosicao() >= ultimaCasa) {
                        String nomeCor = nomeDasCores.get(jogador.getCor());
                        System.out.println(jogador.getCor() + "O jogador " + nomeCor + " venceu o jogo!" + RESET+"\n");
                        jogoTerminado = true;
                        break;
                    }
                }
                totalJogadas++;
            }
            mostrarResultadoFinal();
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void mostrarResultadoFinal() {
        System.out.println("O jogo terminou! Aqui estão os resultados:");
        for (Jogador jogador : listaDeJogadores) {
            String nomeCor = nomeDasCores.get(jogador.getCor());
            System.out.println(jogador.getCor() + "Jogador " + nomeCor + " terminou na casa " + jogador.getPosicao() +
                    " em " + jogador.getJogadas() + " rodadas" + RESET);
        }
    }
}
