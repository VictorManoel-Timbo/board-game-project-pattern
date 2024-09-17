package factory.casas;

import decorator.*;
import models.Casa;
import models.Jogador;

import java.util.ArrayList;

public class CasaTroca extends Casa {
    public CasaTroca(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(ArrayList<Jogador> listaDeJogadores, Jogador jogador) {
        System.out.println("Casa Troca: Você compra um novo equipamento");
        System.out.println("Seus equipamentos são: " + ((jogador.getEquipamentos() == null) ? "Sem Equipamentos" : jogador.getEquipamentos()));

        int indexJogador = listaDeJogadores.indexOf(jogador);

        if (jogador instanceof JogadorComBone) {
            if (jogador.getMoedas() >= 3) {
                System.out.println("Comprou um moletom por 3 moedas (precisa do boné)");
                jogador.setMoedas(jogador.getMoedas() - 6);
                jogador = new JogadorComMoletom(jogador);
            } else {
                System.out.println("Moedas insuficientes para comprar o moletom.");
            }
        } else if (jogador instanceof JogadorComMoletom) {
            if (jogador.getMoedas() >= 4) {
                System.out.println("Comprou óculos escuros por 4 moedas (precisa do boné e moletom)");
                jogador.setMoedas(jogador.getMoedas() - 10);
                jogador = new JogadorComOculosEscuros(jogador);
            } else {
                System.out.println("Moedas insuficientes para comprar os óculos escuros.");
            }
        } else if (jogador instanceof JogadorComOculosEscuros) {
            System.out.println("Você já possui todos os equipamentos.");
        } else {
            if (jogador.getMoedas() >= 2) {
                System.out.println("Comprou um boné por 2 moedas");
                jogador.setMoedas(jogador.getMoedas() - 3);
                jogador = new JogadorComBone(jogador);
            } else {
                System.out.println("Moedas insuficientes para comprar o boné.");
            }
        }

        listaDeJogadores.set(indexJogador, jogador);
    }
}
