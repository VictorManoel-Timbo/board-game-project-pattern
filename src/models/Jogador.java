package models;

public abstract class Jogador {
    protected int moedas;
    protected String equipamentos;

    public String getCor() {
        return cor;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }
    protected String cor;
    protected int posicao;
    protected boolean preso;
    public void setMoedas(int moedas) {
        this.moedas = moedas;
    }
    public int getPosicao() {
        return posicao;
    }
    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public boolean isPreso() {
        return preso;
    }

    public void setPreso(boolean preso) {
        this.preso = preso;
    }

    public abstract int getMoedas();
    public abstract String getEquipamentos();
    public abstract int[] jogarDados();
}