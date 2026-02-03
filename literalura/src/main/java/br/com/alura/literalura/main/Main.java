package br.com.alura.literalura.main;

import br.com.alura.literalura.model.DadosLivros;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;

import java.util.Scanner;

public class Main {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";

    private LivroRepository repository;
    public Main(LivroRepository repository){
        this.repository = repository;
    }
    public void exibirMenu(){
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    ==================================
                    [1] Buscar Livro por Título
                    [2] Listar Livros Registrados
                    [3] Listar Autores Registrados
                    [4] Listar Autores Vivos em um determinado Ano
                    [5] Listar Livros em um determinado Idioma
                    
                    [0] Sair
                    ==================================""";

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroWeb();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEmAno();
                    break;
                case 5:
                    listarLivrosEmIdioma();
                    break;
                case 0:
                    System.out.println("Saindo ...");
                    break;
                default:
                    System.out.println("Opção Inválida! Tente Novamente.");
                    break;
            }
        }

    }

    private void listarLivrosEmIdioma() {

    }

    private void listarAutoresVivosEmAno() {

    }

    private void listarAutoresRegistrados() {

    }

    private void listarLivrosRegistrados() {

    }

    private void buscarLivroWeb() {
        DadosLivros dadosLivros = getDadosLivros();
        Livro livro = new Livro(dadosLivros);
        repository.save(livro);
        System.out.println(livro);
    }

    private DadosLivros getDadosLivros(){
        System.out.println("Informe o nome do LIVRO para a Busca: ");
        var buscaLivro = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + buscaLivro.replace(" ", "%20"));
        DadosLivros dadosLivros = conversor.obterDados(json, DadosLivros.class);
        return dadosLivros;
    }
}