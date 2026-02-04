package br.com.alura.literalura.model.autor;

import br.com.alura.literalura.model.livro.Livro;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer anoNascimento;
    private Integer anoFalecimento;
    @OneToMany(mappedBy = "autor",  cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livrosList = new ArrayList<>();

    public Autor(){}

    public Autor(DadosAutores dadosAutores){
        this.nome = dadosAutores.nome();
        this.anoFalecimento = dadosAutores.anoFalecimento();
        this.anoNascimento = dadosAutores.anoNascimento();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(Integer anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }

    public List<Livro> getLivrosList() {
        return livrosList;
    }

    public void setLivrosList(List<Livro> livrosList) {
        this.livrosList = livrosList;
    }

    @Override
    public String toString() {
        return nome;
    }
}
