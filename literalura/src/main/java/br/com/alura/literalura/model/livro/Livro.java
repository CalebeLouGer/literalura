package br.com.alura.literalura.model.livro;

import br.com.alura.literalura.model.autor.Autor;
import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Autor autor;
    private String titulo;
    private String idioma;
    private Integer numeroDownloads;

    public Livro(){}

    public Livro(DadosLivros dadosLivros){
        this.titulo = dadosLivros.titulo();
        this.numeroDownloads = dadosLivros.numeroDownloads();
        if (dadosLivros.idioma() != null && !dadosLivros.idioma().isEmpty()) {
            this.idioma = dadosLivros.idioma().get(0);
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(Integer numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    @Override
    public String toString() {
        return "Nome: " + titulo + '\n' +
                "Autor: " + autor + '\n' +
                "Idioma: " + idioma + '\n' +
                "Número de Downloads: " + numeroDownloads + '\n' +
                "==============================================";
    }
}
