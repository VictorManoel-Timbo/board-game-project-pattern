package Models.Jogador;

public abstract class Jogador {
    protected String nome;
    protected int posicao;
    protected int moedas;

    public Jogador(String nome) {
        this.nome = nome;
        this.posicao = 0;
        this.moedas = 0;
    }

    public String getNome() {
        return nome;
    }

    public int getPosicao() {
        return posicao;
    }

    public int getMoedas() {
        return moedas;
    }

    public abstract int jogarDados();
    public abstract void aplicarRegraEspecial();

    public void mover(int casas) {
        this.posicao += casas;
    }

    public void adicionarMoeda(int quantidade) {
        this.moedas += quantidade;
    }
}

