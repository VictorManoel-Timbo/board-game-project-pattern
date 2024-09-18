package factory.casas;

import decorator.*;
import models.Casa;
import models.Jogador;

import java.util.ArrayList;
import java.util.Scanner;

public class CasaTroca extends Casa {
    public CasaTroca(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(ArrayList<Jogador> listaDeJogadores, Jogador jogador) {
        System.out.println("Casa Troca: Você compra um novo equipamento");
        System.out.println("Seus equipamentos são: " + ((jogador.getEquipamentos() == null) ? "Sem Equipamentos" : jogador.getEquipamentos()));
        System.out.print("Deseja comprar um novo equipamento? ");
        Scanner input = new Scanner(System.in);
        String resposta = input.nextLine();
        if(resposta.equalsIgnoreCase("sim") || resposta.equalsIgnoreCase("s")) comprarEquipamento(listaDeJogadores, jogador);
    }
    private void comprarEquipamento(ArrayList<Jogador> listaDeJogadores, Jogador jogador){
        int indexJogador = listaDeJogadores.indexOf(jogador);

        if (jogador instanceof JogadorComBone) {
            if (jogador.getMoedas() >= 3) {
                System.out.println("Comprou um moletom por 3 moedas\n");
                jogador.setMoedas(jogador.getMoedas() - 6);
                jogador = new JogadorComMoletom(jogador);
            } else {
                System.out.println("Moedas insuficientes para comprar o moletom.\n");
            }
        } else if (jogador instanceof JogadorComMoletom) {
            if (jogador.getMoedas() >= 4) {
                System.out.println("Comprou óculos escuros por 4 moedas\n");
                jogador.setMoedas(jogador.getMoedas() - 10);
                jogador = new JogadorComOculosEscuros(jogador);
            } else {
                System.out.println("Moedas insuficientes para comprar os óculos escuros.\n");
            }
        } else if (jogador instanceof JogadorComOculosEscuros) {
            System.out.println("Você já possui todos os equipamentos.\n");
        } else {
            if (jogador.getMoedas() >= 2) {
                System.out.println("Comprou um boné por 2 moedas\n");
                jogador.setMoedas(jogador.getMoedas() - 3);
                jogador = new JogadorComBone(jogador);
            } else {
                System.out.println("Moedas insuficientes para comprar o boné.\n");
            }
        }

        listaDeJogadores.set(indexJogador, jogador);
    }
}
