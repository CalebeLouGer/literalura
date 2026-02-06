package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.autor.Autor;
import br.com.alura.literalura.model.livro.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNome(String nome);

    @Query("SELECT a FROM Autor a WHERE a.anoNascimento >= :ano ")
    List<Autor> autoresPorDeterminadoAno(int ano);
}
