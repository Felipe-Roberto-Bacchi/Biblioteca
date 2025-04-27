import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    // Instância única da biblioteca para gerenciar os livros
    private static final Biblioteca biblio = new Biblioteca();

    public static void main(String[] args) {
        // Carrega os livros automaticamente ao iniciar o programa
        carregarLivros();

        // Menu principal do sistema
        String menu = """
                ====== SYSBIBLIO ======
                Escolha uma das opções abaixo:
                1 - Adicionar novo livro
                2 - Pesquisar livro por título
                3 - Listar todos os livros
                4 - Remover livro por título
                5 - Listar livros com mais páginas
                0 - Sair
                """;
        int opcao;
        Scanner lerTeclado = new Scanner(System.in); 
        do {
            // Solicita ao usuário uma opção do menu
            opcao = inputNumerico(lerTeclado, menu);
            switch (opcao) {
                case 1 -> adicionar(lerTeclado); // Adiciona um novo livro
                case 2 -> pesquisarPorTitulo(lerTeclado); // Pesquisa livros por título
                case 3 -> pesquisarTodos(); // Lista todos os livros cadastrados
                case 4 -> removerPorTitulo(lerTeclado); // Remove livros por título
                case 5 -> listarLivrosComMaisPaginas(lerTeclado); // Lista livros com mais páginas
                case 0 -> System.out.println("Encerrando programa ..."); // Sai do programa
                default -> System.out.println("Opção inválida"); // Opção inválida
            }
        } while (opcao != 0);
    }

    // Método para adicionar um novo livro
    private static void adicionar(Scanner lerTeclado) {
        System.out.println("Digite o título do livro:");
        String titulo = lerTeclado.nextLine();
        System.out.println("Digite o autor do livro:");
        String autor = lerTeclado.nextLine();
        int anoPublicacao = inputNumerico(lerTeclado, "Digite o ano da publicação:");
        int numeroPaginas = inputNumerico(lerTeclado, "Digite o número de páginas:");

        Livro novoLivro;

        // Define o tipo do livro (Físico ou Digital)
        int tipoLivro = inputNumerico(lerTeclado, "Qual o tipo do livro: 1-Físico, 2 Digital");
        if (tipoLivro == 1) {
            novoLivro = new LivroFisico();
            System.out.println("Digite as dimensões do livro:");
            String dimensoes = lerTeclado.nextLine();
            int numeroExemplares = inputNumerico(lerTeclado, "Digite o número de exemplares:");

            LivroFisico novoLivroFisico = (LivroFisico) novoLivro;
            novoLivroFisico.setDimensoes(dimensoes);
            novoLivroFisico.setNumeroExemplares(numeroExemplares);
        } else {
            novoLivro = new LivroDigital();
            System.out.println("Digite o formato do arquivo:");
            String formatoArquivo = lerTeclado.nextLine();

            LivroDigital novoLivroDigital = (LivroDigital) novoLivro;
            novoLivroDigital.setFormatoArquivo(formatoArquivo);
        }

        // Define os atributos comuns do livro
        novoLivro.setTitulo(titulo);
        novoLivro.setAutor(autor);
        novoLivro.setAnoPublicacao(anoPublicacao);
        novoLivro.setNumeroPaginas(numeroPaginas);

        // Adiciona o livro à biblioteca e salva automaticamente
        try {
            biblio.adicionar(novoLivro);
            biblio.salvarLivros(); // Salva os livros automaticamente
            System.out.println("Livro adicionado e salvo com sucesso!");
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    // Lista todos os livros cadastrados
    public static void pesquisarTodos() {
        List<Livro> livros = biblio.pesquisarTodos();
        if (livros.isEmpty()) {
            System.out.println("NENHUM LIVRO CADASTRADO");
        } else {
            System.out.println("Livros Cadastrados:");
            for (Livro livro : livros) {
                System.out.println(livro.toString());
            }
        }  
    }

    // Pesquisa livros por título
    private static void pesquisarPorTitulo(Scanner lerTeclado) {
        System.out.println("Digite o título do livro para pesquisa:");
        String titulo = lerTeclado.nextLine();
        List<Livro> livrosEncontrados = biblio.pesquisarPorTitulo(titulo);
        if (livrosEncontrados.isEmpty()) {
            System.out.println("Nenhum livro encontrado com o título informado.");
        } else {
            System.out.println("Livros encontrados:");
            for (Livro livro : livrosEncontrados) {
                System.out.println(livro.toString() + 
                                   " - Formato: " + livro.GetTipo() +
                                   " - Tempo de publicação: " + livro.calcularTempoPublicacao(0) + " anos");
            }
        }
    }

    // Remove livros por título
    private static void removerPorTitulo(Scanner lerTeclado) {
        System.out.println("Digite o título do livro para remoção:");
        String titulo = lerTeclado.nextLine();
        int removidos = biblio.removerPorTitulo(titulo);
        try {
            biblio.salvarLivros(); // Salva os livros automaticamente após remoção
            System.out.println(removidos + " livro(s) removido(s) e alterações salvas.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os livros: " + e.getMessage());
        }
    }

    // Lista livros com mais páginas que o número informado
    private static void listarLivrosComMaisPaginas(Scanner lerTeclado) {
        int paginas = inputNumerico(lerTeclado, "Digite o número mínimo de páginas:");
        List<Livro> livros = biblio.listarLivrosComMaisPaginas(paginas);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado com mais de " + paginas + " páginas.");
        } else {
            System.out.println("Livros com mais de " + paginas + " páginas:");
            for (Livro livro : livros) {
                System.out.println(livro.toString() + 
                                   " - Formato: " + livro.GetTipo() +
                                   " - Tempo de publicação: " + livro.calcularTempoPublicacao(0) + " anos");
            }
        }
    }

    // Método para carregar os livros automaticamente ao iniciar
    private static void carregarLivros() {
        try {
            biblio.carregarLivros();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar os livros: " + e.getMessage());
        }
    }

    // Método para entrada numérica com validação
    private static int inputNumerico(Scanner lerTeclado, String mensagem) {
        int valor = 0;
        boolean entradaValida = false;
        System.out.println(mensagem);
        do {
            String valorStr = lerTeclado.nextLine();
            try {
                valor = Integer.parseInt(valorStr);
                entradaValida = true;
            } catch (NumberFormatException e) { // Captura exceção específica
                System.out.println("Erro. Por favor informe um número Inteiro");
            }
        } while (!entradaValida);
        return valor;
    }
}
