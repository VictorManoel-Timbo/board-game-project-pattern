package models;

public abstract class Casa {
    protected int numero;
    public abstract void aplicarRegra(Jogador jogador);

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumero() {return this.numero;}
}
