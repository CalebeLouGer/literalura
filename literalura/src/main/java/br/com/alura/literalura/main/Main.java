package br.com.alura.literalura.main;

import br.com.alura.literalura.model.*;
import br.com.alura.literalura.model.autor.Autor;
import br.com.alura.literalura.model.autor.DadosAutores;
import br.com.alura.literalura.model.livro.DadosLivros;
import br.com.alura.literalura.model.livro.Livro;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
    private List<Autor> autorList;
    private Optional<Livro> livroOptional;

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
            }else {
                System.out.println("Autor ou Livro não existem.");
            }
            livroRepository.save(livro);
            System.out.println(livro);
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

    private void listarAutoresRegistrados() {
        autorList = autorRepository.findAll();
        autorList.stream()
                .sorted(Comparator.comparing(Autor::getNome))
                .forEach(System.out::println);
    }

    private void listarAutoresVivosEmAno() {
        System.out.println("Informe a partir de que ANO que deseja: ");
        int anoBuscado = leitura.nextInt();
        leitura.nextLine();

        List<Autor> autorData = autorRepository.autoresPorDeterminadoAno(anoBuscado);
        if (autorData.isEmpty()) {
            System.out.println("Nenhum autor encontrado.");
            return;
        }
        autorData.forEach(a ->
                System.out.printf("Ano de Nascimento: %s | Ano de Falecimento: %s | Nome: %s\n",
                        a.getAnoNascimento(),
                        a.getAnoFalecimento(),
                        a.getNome()));
    }

    private void listarLivrosEmIdioma() {
        System.out.println("Informe a SIGLA do Idioma que deseja: ");
        var idiomaBuscado = leitura.nextLine();
        livrosList = livroRepository.findByIdioma(idiomaBuscado);
        livrosList.stream()
                .sorted(Comparator.comparing(Livro::getTitulo))
                .forEach(System.out::println);
    }

    private void buscarLivroPorTitulo(){
        System.out.println("Infome o Título do Livro a ser Buscado: ");
        var livroBuscado = leitura.nextLine();
        livroOptional = livroRepository.findByTituloContainingIgnoreCase(livroBuscado);

        if (livroOptional.isPresent()){
            System.out.println(livroOptional.get());
        }else{
            System.out.println("Livro não Encontrado!");
        }
    }
}