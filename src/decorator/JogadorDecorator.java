package decorator;

import models.Jogador;

public abstract class JogadorDecorator extends Jogador {
    protected Jogador jogadorDecorado;

    public JogadorDecorator(Jogador jogadorDecorado) {
        super(jogadorDecorado);
        this.jogadorDecorado = jogadorDecorado;
    }

    @Override
    public void setMoedas(int moedas) {
        jogadorDecorado.setMoedas(moedas);
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
    public int[] jogarDados() {
        return jogadorDecorado.jogarDados();
    }
}
