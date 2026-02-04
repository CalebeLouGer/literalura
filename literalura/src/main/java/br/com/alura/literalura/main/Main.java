package br.com.alura.literalura.main;

import br.com.alura.literalura.model.*;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

@Component
public class Main {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private AutorRepository autorRepository;


    private List<Livro> livrosList;
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
                    System.out.println("Saindo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção Inválida!");
            }
        }
    }

    private void buscarLivroWeb() {
        List<DadosLivros> livros = getDadosLivros();
        for (DadosLivros dados : livros) {
            Livro livro = new Livro(dados);
            if (dados.autor() != null && !dados.autor().isEmpty()) {
                DadosAutores dadosAutor = dados.autor().get(0);
                Autor autor = autorRepository.findByNome(dadosAutor.nome())
                        .orElseGet(() -> autorRepository.save(new Autor(dadosAutor)));
                livro.setAutor(autor);
                autor.getLivrosList().add(livro);
            }
            livroRepository.save(livro);
        }
    }
    private List<DadosLivros> getDadosLivros(){
        System.out.println("Informe o nome do LIVRO para a Busca: ");
        var buscaLivro = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + buscaLivro.replace(" ", "+"));
        GutendexResponse resposta = conversor.obterDados(json, GutendexResponse.class);
        return resposta.results();
    }
    private void listarLivrosRegistrados() {
        livrosList = livroRepository.findAll();
        livrosList.stream()
                .sorted(Comparator.comparing(Livro::getTitulo))
                .forEach(System.out::println);
    }

    private void listarLivrosEmIdioma() {

    }

    private void listarAutoresVivosEmAno() {

    }

    private void listarAutoresRegistrados() {

    }


}