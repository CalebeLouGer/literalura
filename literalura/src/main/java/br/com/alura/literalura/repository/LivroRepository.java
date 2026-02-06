package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.livro.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByIdioma(String idiomaBuscado);

    Optional<Livro> findByTituloContainingIgnoreCase(String livroBuscado);
}
