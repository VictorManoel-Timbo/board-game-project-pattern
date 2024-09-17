package factory.jogadores;

import java.util.Random;
import models.Jogador;

public class JogadorSortudo extends Jogador {
    public JogadorSortudo (Jogador jogador) {
        super(jogador);
    }
    public JogadorSortudo(){}

    @Override
    public int getMoedas() {
        return moedas;
    }
    @Override
    public String getEquipamentos() {
        return (equipamentos==null)?"Sem Equipamentos":equipamentos;
    }
    @Override
    public int[] jogarDados() {
        Random random = new Random();
        int dado1, dado2, soma;
        do {
            dado1 = random.nextInt(6) + 1;
            dado2 = random.nextInt(6) + 1;
            soma = dado1 + dado2;
        } while (soma < 7);
        return new int[]{dado1, dado2};
    }
}
