public final class LivroDigital extends Livro {
    // Tamanho do arquivo digital em MB
    private double tamanhoArquivo;
    // Formato do arquivo (ex: PDF, EPUB)
    private String formatoArquivo;

    // Retorna o tamanho do arquivo
    public double getTamanhoArquivo() {
        return tamanhoArquivo;
    }

    // Define o tamanho do arquivo
    public void setTamanhoArquivo(double tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }

    // Retorna o formato do arquivo
    public String getFormatoArquivo() {
        return formatoArquivo;
    }

    // Define o formato do arquivo
    public void setFormatoArquivo(String formatoArquivo) {
        this.formatoArquivo = formatoArquivo;
    }

    // Retorna o tipo do livro como "Digital"
    @Override
    public String GetTipo() {
        return "Digital";
    }
}
