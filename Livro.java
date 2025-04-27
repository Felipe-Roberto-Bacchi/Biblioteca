import java.io.Serializable;
import java.time.LocalDate;

public abstract class Livro implements Serializable {
    private static final long serialVersionUID = 1L; // Versão para serialização
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private int numeroPaginas;

    public abstract String GetTipo();

    public int calcularTempoPublicacao(int paginasLidasPorDia) {
        int anoAtual = LocalDate.now().getYear();
        return anoAtual - getAnoPublicacao();
    }
 
  
    @Override //annotations
    public String toString() {
        String descricao = 
            "Título: " + getTitulo() +
            " - Autor: " + getAutor() +
            " - Ano: " + getAnoPublicacao();
        return descricao;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public int getAnoPublicacao() {
        return anoPublicacao;
    }
    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }
    public int getNumeroPaginas() {
        return numeroPaginas;
    }
    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }
    
}