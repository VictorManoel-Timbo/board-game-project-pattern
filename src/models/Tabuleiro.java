package models;

import factory.casas.CasaFactory;
import factory.casas.CasaSimples;
import factory.jogadores.JogadorFactory;

import java.util.*;

import static models.Cores.*;

public class Tabuleiro {
    private static Tabuleiro instancia;
    private int qtdCasas;

    private void setQtdCasas(int qtdCasas) {
        this.qtdCasas = qtdCasas;
    }

    private final Scanner input = new Scanner(System.in);
    private final ArrayList<Jogador> listaDeJogadores = new ArrayList<>();
    private final ArrayList<Casa> listaDeCasas = new ArrayList<>();
    private int ultimaCasa;
    private boolean modoDebug = false;
    public static final int LIMITE_JOGADORES = 6;
    public static final int LIMITE_CASAS = 40;
    public static final double PERCENTUAL_MAX_CASAS_ESPECIAIS = 0.5;

    public static Tabuleiro instancia() {
        if (instancia == null) {
            instancia = new Tabuleiro();
        }
        return instancia;
    }

    public void mostrarEstado() {
        for (Jogador jogador : listaDeJogadores) {
            String nomeCor = nomeDasCores.get(jogador.getCor());
            System.out.println(jogador.getCor() + "Jogador " + nomeCor + " na casa " + jogador.getPosicao() + " com " + jogador.getMoedas() + " moedas e equipamentos: " + jogador.getEquipamentos() + RESET);
        }
    }

    private void jogarRodada(Jogador jogadorAtual, boolean modoDebug) {
        jogadorAtual.jogada();
        System.out.println("\nÉ a vez do jogador: " + jogadorAtual.getCor() + nomeDasCores.get(jogadorAtual.getCor()) + RESET);
        if (jogadorAtual.isPreso()) {
            String nomeCor = nomeDasCores.get(jogadorAtual.getCor());
            System.out.println(jogadorAtual.getCor() + "Jogador " + nomeCor + " está preso."+ RESET);
            libertarJogador(jogadorAtual, modoDebug);

        } else {
            if (modoDebug) {
                realizarJogadaDebug(jogadorAtual);
            } else {
                realizarJogadaNormal(jogadorAtual);
            }
        }
    }

    private void libertarJogador(Jogador jogadorAtual, boolean modoDebug){
        String nomeCor = nomeDasCores.get(jogadorAtual.getCor());
        System.out.println("Faltam " + (jogadorAtual.getJogadas() - jogadorAtual.getJogadaPreso() + 1) + " rodadas para ser liberto" );
        if (jogadorAtual.getJogadas() - jogadorAtual.getJogadaPreso() > 2) {
            jogadorAtual.setPreso(false);
            System.out.println("Jogador " + nomeCor + " cumpriu as 2 rodadas e está livre para jogar.");
            jogarRodada(jogadorAtual, modoDebug);
        } else {
            System.out.println("Deseja pagar 2 moedas para sair da prisão? (1 para sim, 2 para não)");
            Scanner input = new Scanner(System.in);
            int escolha = input.nextInt();

            if (escolha == 1) {
                if (jogadorAtual.pagarTaxaParaSair()) {
                    jogadorAtual.setPreso(false);
                    jogarRodada(jogadorAtual, modoDebug);
                } else {
                    System.out.println("Jogador não pode pagar e continuará preso.\n");
                }
            } else {
                System.out.println("Jogador " + nomeCor + " optou por não pagar e continuará preso.\n");
            }
        }
    }
    private void realizarJogadaNormal(Jogador jogador) {
        int[] dados = jogador.jogarDados();
        int somaDados = dados[0] + dados[1];
        String nomeCor = nomeDasCores.get(jogador.getCor());
        System.out.println(jogador.getCor() + "Jogador " + nomeCor + " jogou os dados: " + dados[0] + " e " + dados[1] + ". Avança: " + somaDados +" posições"+ RESET);

        int novaPosicao = jogador.getPosicao() + somaDados;
        jogador.setPosicao(novaPosicao);
        System.out.println("Jogador " + nomeCor + " moveu para a casa " + novaPosicao + RESET + "\n");
        verificarCasaEspecial(jogador);
    }

    private void realizarJogadaDebug(Jogador jogador) {
        String nomeCor = nomeDasCores.get(jogador.getCor());
        System.out.println("Modo debug: Escolha a casa para o Jogador " + nomeCor + " (1 a " + qtdCasas + "): ");
        Scanner input = new Scanner(System.in);
        int novaPosicao = input.nextInt();

        if (novaPosicao < 1 || novaPosicao > qtdCasas) {
            System.out.println("Posição inválida. Por favor, escolha uma casa entre 1 e " + qtdCasas);
            realizarJogadaDebug(jogador);
        } else {
            jogador.setPosicao(novaPosicao);
            System.out.println("Jogador " + nomeCor + " moveu diretamente para a casa " + novaPosicao + RESET + "\n");
        }
        verificarCasaEspecial(jogador);
    }

    private void verificarCasaEspecial(Jogador jogador) {
        for (Casa casa : listaDeCasas) {
            if (jogador.getPosicao() == casa.getNumero()) {
                casa.aplicarRegra(listaDeJogadores, jogador);
                break;
            }
        }
    }

    public void setarJogadores(int numJogadores) {
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

    public void setarCasas(int numCasas) {
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
    public void isFinished() {
        try {
            System.out.println("Deseja ativar o modo debug? (1 para sim, 2 para não)");
            int escolhaDebug = input.nextInt();
            if (escolhaDebug == 1) {
                modoDebug = true;
                System.out.println("Modo debug ativado.");
            }

            setQtdCasas(ultimaCasa);
            boolean jogoTerminado = false;

            while (!jogoTerminado) {
                for (Jogador jogador : listaDeJogadores) {
                    if (listaDeJogadores.get(0).getJogadas() != 0) mostrarEstado();
                    jogarRodada(jogador, modoDebug);

                    if (jogador.getPosicao() >= ultimaCasa) {
                        String nomeCor = nomeDasCores.get(jogador.getCor());
                        System.out.println(jogador.getCor() + "O jogador " + nomeCor + " venceu o jogo!" + RESET+"\n");
                        jogoTerminado = true;
                        break;
                    }
                }
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
