package factory.jogadores;

import java.util.Random;
import models.Jogador;

public class JogadorAzarado extends Jogador {
    public JogadorAzarado (Jogador jogador) {
        this.moedas = jogador.getMoedas();
        this.equipamentos = jogador.getEquipamentos();
        this.posicao = jogador.getPosicao();
    }
    public JogadorAzarado(){};
    @Override
    public int getMoedas() {
        return moedas;
    }
    @Override
    public String getEquipamentos() {
        return equipamentos;
    }
    @Override
    public int[] jogarDados() {
        Random random = new Random();
        int dado1, dado2, soma;
        do {
            dado1 = random.nextInt(6) + 1;
            dado2 = random.nextInt(6) + 1;
            soma = dado1 + dado2;
        } while (soma >= 7);
        return new int[]{dado1, dado2};
    }
}

