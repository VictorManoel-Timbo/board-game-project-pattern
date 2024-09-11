package factory.jogadores;

import java.util.Random;
import models.Jogador;

public class JogadorComum extends Jogador {
    public JogadorComum (Jogador jogador) {
        this.moedas = jogador.getMoedas();
        this.equipamentos = jogador.getEquipamentos();
        this.posicao = jogador.getPosicao();
    }
    public JogadorComum(){};
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
        int dado1 = random.nextInt(6) + 1;
        int dado2 = random.nextInt(6) + 1;
        return new int[]{dado1, dado2};
    }
}