public final class LivroFisico extends Livro {
    // Número de exemplares disponíveis
    private int numeroExemplares;
    // Dimensões físicas do livro (ex: 20x30cm)
    private String dimensoes;

    // Retorna o número de exemplares
    public int getNumeroExemplares() {
        return numeroExemplares;
    }

    // Define o número de exemplares
    public void setNumeroExemplares(int numeroExemplares) {
        this.numeroExemplares = numeroExemplares;
    }

    // Retorna as dimensões do livro
    public String getDimensoes() {
        return dimensoes;
    }

    // Define as dimensões do livro
    public void setDimensoes(String dimensoes) {
        this.dimensoes = dimensoes;
    }

    // Retorna o tipo do livro como "Físico"
    @Override
    public String GetTipo() {
        return "Físico";
    }
}
