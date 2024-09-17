package models;

import java.util.ArrayList;

public abstract class Casa {
    protected int numero;
    public Casa(int numero) {
        this.numero = numero;
    }
    public abstract void aplicarRegra(ArrayList<Jogador> listaDeJogadores, Jogador jogador);

    public int getNumero() {return this.numero;}
}
