package models;

public abstract class Jogador {
    protected int moedas;
    protected String equipamentos;
    protected int jogadas; // Contador de jogadas
    protected String cor;
    protected int posicao;
    protected boolean preso;
    protected int jogadaPreso; // Armazena a jogada em que o jogador foi preso

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

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

    public int getJogadas() {
        return jogadas;
    }

    public void jogada() {
        this.jogadas += 1;
    }

    public void setJogadaPreso(int jogadaPreso) {
        this.jogadaPreso = jogadaPreso;
    }

    public int getJogadaPreso() {
        return jogadaPreso;
    }

    // Método que verifica se o jogador pode pagar a taxa para sair da prisão
    public boolean pagarTaxaParaSair() {
        if (this.moedas >= 2) {
            this.moedas -= 2;
            System.out.println("Jogador pagou 2 moedas para sair da prisão. Moedas restantes: " + this.moedas);
            return true;
        } else {
            System.out.println("Jogador não tem moedas suficientes para pagar a taxa.");
            return false;
        }
    }

    public abstract int getMoedas();
    public abstract String getEquipamentos();
    public abstract int[] jogarDados();
}
