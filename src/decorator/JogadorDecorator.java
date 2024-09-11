package decorator;

import models.Jogador;

public class JogadorDecorator extends Jogador {
    protected Jogador jogadorDecorado;
    public JogadorDecorator(Jogador JogadorDecorator) {
        this.jogadorDecorado = JogadorDecorator;
    }
    @Override
    public int getMoedas() {
        return jogadorDecorado.getMoedas();
    }
    @Override
    public String getEquipamentos() {
        return jogadorDecorado.getEquipamentos();
    }
    @Override
    public int[] jogarDados() { return jogadorDecorado.jogarDados(); }

}