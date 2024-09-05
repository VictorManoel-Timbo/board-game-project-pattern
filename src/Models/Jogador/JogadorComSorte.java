package Models.Jogador;

public class JogadorComSorte extends Jogador {
    public JogadorComSorte(String nome) {
        super(nome);
    }

    @Override
    public int jogarDados() {
        return 7 + (int)(Math.random() * 5); // Garante que será >= 7
    }

    @Override
    public void aplicarRegraEspecial() {
        // Regras especiais para jogador com sorte
    }
}