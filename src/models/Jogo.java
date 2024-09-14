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
        int numJogadores = 0;
        while (true) {
            try {
                System.out.println("Digite o número de jogadores (máximo de " + LIMITE_JOGADORES + "):");
                numJogadores = input.nextInt();
                if (numJogadores <= 1 || numJogadores > LIMITE_JOGADORES) {
                    throw new IllegalArgumentException("Número de jogadores deve ser entre 1 e " + LIMITE_JOGADORES);
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                input.next();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        for (int i = 0; i < numJogadores; i++) {
            while (true) {
                try {
                    System.out.println("""
                            Selecione um tipo de jogador:
                            1 - Jogador Comum
                            2 - Jogador Azarado
                            3 - Jogador Sortudo""");
                    int tipo = input.nextInt();
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
        int numCasas = 0;
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
        int numCasasEspeciais = 0;
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

                    System.out.println("Digite o número da casa especial (de 1 a " + numCasas + "): ");
                    int numeroCasaEspecial = input.nextInt();

                    Casa casaEspecial = CasaFactory.criarCasa(tipo, numeroCasaEspecial);
                    listaDeCasas.add(casaEspecial);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                    input.next();
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
            boolean jogoTerminado = false;

            while (!jogoTerminado) {
                for (Jogador jogador : listaDeJogadores) {
                    tabuleiro.mostrarEstado(jogador);
                    jogarRodada(jogador);

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

    private void jogarRodada(Jogador jogador) {
        if (jogador.isPreso()) {
            libertarJogador(jogador);
        } else {
            if (modoDebug) {
                realizarJogadaDebug(jogador);
            } else {
                realizarJogadaNormal(jogador);
            }
        }
        jogador.jogada();
        verificarCasaEspecial(jogador);
    }

    private void libertarJogador(Jogador jogadorAtual){
        String nomeCor = nomeDasCores.get(jogadorAtual.getCor());
        if (jogadorAtual.getJogadas() - jogadorAtual.getJogadaPreso() >= 2) {
            jogadorAtual.setPreso(false);
            System.out.println("Jogador " + nomeCor + " cumpriu as 2 rodadas e está livre para jogar.");
            jogarRodada(jogadorAtual);
        } else {
            System.out.println("Deseja pagar 2 moedas para sair da prisão? (1 para sim, 2 para não)");
            Scanner input = new Scanner(System.in);
            int escolha = input.nextInt();

            if (escolha == 1) {
                if (jogadorAtual.pagarTaxaParaSair()) {
                    jogadorAtual.setPreso(false);
                    jogarRodada(jogadorAtual);
                } else {
                    System.out.println("Jogador não pode pagar e continuará preso.");
                }
            } else {
                System.out.println("Jogador " + nomeCor + " optou por não pagar e continuará preso.");
            }
        }
    }

    private void realizarJogadaNormal(Jogador jogador) {
        int[] dados = jogador.jogarDados();
        int somaDados = dados[0] + dados[1];
        String nomeCor = nomeDasCores.get(jogador.getCor());
        System.out.println(jogador.getCor() + "Jogador " + nomeCor + " jogou os dados: " + dados[0] + " e " + dados[1] + ". Soma: " + somaDados + RESET);

        int novaPosicao = jogador.getPosicao() + somaDados;
        jogador.setPosicao(novaPosicao);
        System.out.println("Jogador " + nomeCor + " moveu para a casa " + novaPosicao + RESET + "\n");
    }

    private void realizarJogadaDebug(Jogador jogador) {
        String nomeCor = nomeDasCores.get(jogador.getCor());
        System.out.println("Modo debug: Escolha a casa para o Jogador " + nomeCor + " (1 a " + ultimaCasa + "): ");
        int novaPosicao = input.nextInt();

        if (novaPosicao < 1 || novaPosicao > ultimaCasa) {
            System.out.println("Posição inválida. Por favor, escolha uma casa entre 1 e " + ultimaCasa);
            realizarJogadaDebug(jogador);
        } else {
            jogador.setPosicao(novaPosicao);
            System.out.println("Jogador " + nomeCor + " moveu diretamente para a casa " + novaPosicao + RESET + "\n");
        }
    }

    private void verificarCasaEspecial(Jogador jogador) {
        for (Casa casa : listaDeCasas) {
            if (jogador.getPosicao() == casa.getNumero()) {
                casa.aplicarRegra(jogador);
                break;
            }
        }
    }

    private void mostrarResultadoFinal() {
        System.out.println("O jogo terminou! Aqui estão os resultados:");
        for (Jogador jogador : listaDeJogadores) {
            String nomeCor = nomeDasCores.get(jogador.getCor());
            System.out.println(jogador.getCor() + "Jogador " + nomeCor + " terminou na casa " + jogador.getPosicao() +
                    " em " + jogador.getJogadas() + " rodadas" + RESET);
        }
        System.out.println("Número total de jogadas: " + totalJogadas);
    }
}
