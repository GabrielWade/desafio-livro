package com.example.demo.principal;

import com.example.demo.model.*;
import com.example.demo.repository.AutorRepository;
import com.example.demo.repository.LivroRepository;
import com.example.demo.service.ConsumoApi;
import com.example.demo.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Scanner;
@Component
public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private static final String ENDERECO = "https://gutendex.com/books/";

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }
    public void exibirMenu() {
        var opcao = -1;
        while (opcao != 6) {

            System.out.println(
                    "Bem-vindo!\n" +
                            "1 - Pesquise um livro\n" +
                            "2 - Listar todos os livros\n" +
                            "3 - Listar todos os atores\n" +
                            "4 - Listar atores vivos em um determinado ano\n" +
                            "5 - Listar livros de um determinado idioma\n" +
                            "6 - Sair\n"
            );
            System.out.print("Digite a opção desejada: ");
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivro();
                    break;
                case 2:
                    System.out.println("Listar todos os livros");
                    break;
                case 3:
                    System.out.println("Listar todos os atores");
                    break;
                case 4:
                    System.out.println("Listar atores vivos em um determinado ano");
                    break;
                case 5:
                    System.out.println("Listar livros de um determinado idioma");
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }
    private void buscarLivro() {
        List<DadosLivro> dadosLivro = getDadosLivro();
        DadosLivro primeiroLivroDados = dadosLivro.get(0);
        Livro livro = new Livro(primeiroLivroDados);

        String autorLivro = primeiroLivroDados.autores().get(0).nome();
        Autor autor = autorRepository.findByNameContainingIgnoreCase(autorLivro);
        if (autor == null) {
            autor = new Autor(primeiroLivroDados.autores().get(0));
            autorRepository.save(autor);
            livro.setAutor(autor);
        } else {
            livro.setAutor(autor);
            autorRepository.save(autor);
        }
        livroRepository.save(livro);
    }
    private List<DadosLivro> getDadosLivro() {
        System.out.println("Digite o nome do livro: ");
        String nomeLivro = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + "?search=" + nomeLivro.replace(" ", "%20"));
        OutsideObject outsideObject = conversor.obterDados(json, OutsideObject.class);
        List<DadosLivro> dadosLivros = outsideObject.getResults();
        return dadosLivros;
    }

    private void printLivro(Livro livro) {
        System.out.println("--------------------------Livro--------------------------");
        System.out.println("Livro: " + livro.getTitulo());
        System.out.println("Autor: " + livro.getAutor().getName());
        System.out.println("Idioma: " + livro.getIdioma());
        System.out.println("Download Count: " + livro.getDownloadCount());
        System.out.println("---------------------------------------------------------");
    }

}
