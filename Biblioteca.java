import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    // Lista que armazena todos os livros cadastrados
    private final List<Livro> acervo = new ArrayList<>();
    private final int ANO_PUBLICACAO_MINIMO = 1400; // Ano mínimo permitido para publicação
    private final String ARQUIVO_LIVROS = "biblioteca.dat"; // Nome do arquivo para persistência

    // Adiciona um novo livro ao acervo
    public Livro adicionar(Livro novoLivro) throws Exception {
        if (novoLivro.getTitulo() == null || novoLivro.getTitulo().isEmpty())
            throw new Exception("Título inválido!");
        if (novoLivro.getAutor() == null || novoLivro.getAutor().isEmpty())
            throw new Exception("Autor inválido!");
        if (novoLivro.getAnoPublicacao() < ANO_PUBLICACAO_MINIMO)
            throw new Exception("Ano de publicação inválido!");
        acervo.add(novoLivro); // Adiciona o livro ao acervo

        return novoLivro;
    }

    // Pesquisa livros no acervo que contenham o título informado
    public List<Livro> pesquisarPorTitulo(String titulo) {
        List<Livro> livrosEncontrados = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                livrosEncontrados.add(livro);
                // Exibe informações do livro encontrado
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Ano de publicação: " + livro.getAnoPublicacao());
                System.out.println("Tempo de publicação: " + livro.calcularTempoPublicacao(0) + " anos ");
                System.out.println("-----------------------------");
            }
        }
        if (livrosEncontrados.isEmpty()) {
            System.out.println("Nenhum livro encontrado com o título informado.");
        }
        return livrosEncontrados;
    }

    // Retorna todos os livros cadastrados no acervo
    public List<Livro> pesquisarTodos() {
        return acervo;
    }

    // Remove livros do acervo que contenham o título informado
    public int removerPorTitulo(String titulo) {
        List<Livro> livrosParaRemover = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                livrosParaRemover.add(livro);
            }
        }
        acervo.removeAll(livrosParaRemover); // Remove os livros encontrados
        return livrosParaRemover.size(); // Retorna a quantidade de livros removidos
    }

    // Lista livros com mais páginas que o número informado
    public List<Livro> listarLivrosComMaisPaginas(int paginas) {
        List<Livro> livrosFiltrados = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getNumeroPaginas() > paginas) {
                livrosFiltrados.add(livro);
            }
        }
        return livrosFiltrados;
    }

    // Salva os livros no arquivo
    public void salvarLivros() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_LIVROS))) {
            oos.writeObject(acervo);
            System.out.println("Livros salvos com sucesso!");
        }
    }

    // Carrega os livros do arquivo
    @SuppressWarnings("unchecked")
    public void carregarLivros() throws IOException, ClassNotFoundException {
        File arquivo = new File(ARQUIVO_LIVROS);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_LIVROS))) {
                List<Livro> livrosCarregados = (List<Livro>) ois.readObject();
                acervo.clear();
                acervo.addAll(livrosCarregados);
                System.out.println("Livros carregados com sucesso!");
            }
        } else {
            System.out.println("Nenhum arquivo de livros encontrado. Começando com uma biblioteca vazia.");
        }
    }
}


