package br.com.alura.literalura.model;

import br.com.alura.literalura.model.livro.DadosLivros;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GutendexResponse(List<DadosLivros> results){
}

