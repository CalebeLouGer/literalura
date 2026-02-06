# 📚 Literalura

**Literalura** é uma aplicação em Java que gerencia um catálogo de livros e seus respectivos autores.
Ela permite que você cadastre livros, consulte autores e faça filtros personalizados baseado em dados como ano de nascimento, entre outras funcionalidades.

---

## 🧠 Objetivos do projeto

* Praticar conceitos de Java — Orientação a Objetos (POO) e estrutura de dados;
* Utilizar JPA (Java Persistence API) com Spring Data para persistência;
* Aprender a construir consultas personalizadas com Repository e JPQL;
* Criar uma aplicação de console interativa;
* Simular um catálogo de livros com autores.

---

## 🛠 Tecnologias utilizadas

O projeto foi construído com:

* 🟦 **Java**
* 🌱 **Spring Boot**
* 📦 **Spring Data JPA**
* 🗄 **Banco de dados relacional (PostgreSQL)**
* 🧠 Conceitos de Orientação a Objetos e boas práticas

---

## 🚀 Como usar

### 1. Clone o repositório

```bash
git clone https://github.com/CalebeLouGer/literalura.git
```

### 2. Abra em sua IDE (Ex: IntelliJ, Eclipse)

Importe como um projeto Maven/Gradle.

### 3. Execute a aplicação

Com Spring Boot:

```bash
mvn spring-boot:run
```

ou

```bash
gradle bootRun
```

(Segundo a configuração que você estiver usando).

### 4. Siga as instruções no console

A aplicação roda em modo texto — basta digitar opções do menu fornecidas na inicialização.

---

## 📌 Funcionalidades

* Cadastro de **Autores** com nome, ano de nascimento e ano de falecimento;
* Cadastro de **Livros** com título, autor e métricas (como downloads);
* Consulta de livros cadastrados;
* Filtro de autores a partir de um ano de nascimento;
* Impressão de dados de forma formatada no console;
* Validação de entradas do usuário.

---

## 📁 Estrutura do projeto

A estrutura básica é:

```
src/
├─ main/
│   ├─ java/
│   │   └─ br/com/alura/literalura/
│   │       ├─ main/          # Classes de menu e execução
│   │       ├─ model/         # Entidades (Autor, Livro)
│   │       ├─ repository/    # Repositórios JPA
│   │       └─ service/       # Lógica de negócio
│   └─ resources/
│       └─ application.properties
```

---

## 🧪 Exemplos de uso

Ao iniciar a aplicação, você pode:

```
1. Cadastrar Autor
2. Cadastrar Livro
3. Listar Livros
4. Listar Autores a partir de ano de nascimento
5. Sair
```

Exemplo de saída no console:

```
Nome: Machado de Assis | Ano de Nascimento: 1839 | Ano de Falecimento: 1908
```

---

## 🤝 Como contribuir

Contribuições são bem-vindas! Para contribuir:

1. **Fork** este repositório;
2. Crie uma nova branch:

```bash
git checkout -b feature/nova-funcionalidade
```

3. Faça suas alterações;
4. **Commit** suas mudanças:

```bash
git commit -m "Descrição da sua contribuição"
```

5. **Push** para sua branch:

```bash
git push origin feature/nova-funcionalidade
```

6. Abra um **Pull Request** explicando sua implementação.

---
