package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNome(String nome);
}
