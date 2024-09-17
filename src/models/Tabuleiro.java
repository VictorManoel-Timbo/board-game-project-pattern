package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import static models.Jogo.*;

public class Tabuleiro {
    private static Tabuleiro instancia;
    private final ArrayList<Jogador> listaDeJogadores;
    private final ArrayList<Casa> listaDeCasas;
    private int qtdCasas;

    public void setQtdCasas(int qtdCasas) {
        this.qtdCasas = qtdCasas;
    }

    HashMap<String, String> nomeDasCores = new HashMap<>() {{
        put(RED, "vermelho");
        put(GREEN, "verde");
        put(YELLOW, "amarelo");
        put(BLUE, "azul");
        put(PURPLE, "roxo");
        put(CIANO, "ciano");
    }};

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

    public void mostrarEstado(Jogador jogadorAtual) {
        for (Jogador jogador : listaDeJogadores) {
            String nomeCor = nomeDasCores.get(jogador.getCor());
            System.out.println(jogador.getCor() + "Jogador " + nomeCor + " na casa " + jogador.getPosicao() + " com " + jogador.getMoedas() + " moedas e equipamentos: " + jogador.getEquipamentos() + RESET);
        }
        System.out.println("\nÉ a vez do jogador: " + nomeDasCores.get(jogadorAtual.getCor()));
    }

    public void jogarRodada(Jogador jogadorAtual, boolean modoDebug) {
        jogadorAtual.jogada();
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
}
