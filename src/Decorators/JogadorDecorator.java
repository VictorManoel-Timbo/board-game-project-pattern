package Decorators;

import Models.Jogador.Jogador;

abstract class JogadorDecorator extends Jogador {
    protected Jogador jogadorDecorado;

    public JogadorDecorator(Jogador jogador) {
        super(jogador.getNome());
        this.jogadorDecorado = jogador;
    }

    @Override
    public int getMoedas() {
        return jogadorDecorado.getMoedas();
    }

    @Override
    public int getPosicao() {
        return jogadorDecorado.getPosicao();
    }

    @Override
    public abstract void aplicarRegraEspecial();
}