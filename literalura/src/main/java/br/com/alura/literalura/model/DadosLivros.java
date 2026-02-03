package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivros(@JsonAlias("title") String titulo,
                          @JsonAlias("name") String autor,
                          @JsonAlias("languages") String idioma,
                          @JsonAlias("download_count") Integer numeroDownloads) {
}
