package com.example.demo.principal;

import com.example.demo.model.*;
import com.example.demo.repository.AutorRepository;
import com.example.demo.repository.LivroRepository;
import com.example.demo.service.ConsumoApi;
import com.example.demo.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private static final String ENDERECO = "https://gutendex.com/books/";

    private LivroRepository repositorio;
    private AutorRepository autorRepository;
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
        List<DadosLivro> livros = getDadosLivro();
        List<Livro> livrosConvertidos = livros.stream().map(Livro::new).collect(Collectors.toList());
        repositorio.saveAll(livrosConvertidos);
        List<DadosAutor> autores = getDadosAutor(livros);
        List<Autor> autoresConvertidos = autores.stream().map(Autor::new).collect(Collectors.toList());

        System.out.println("Livros encontrados: ");
        livrosConvertidos.forEach(livro -> System.out.println(livro.getTitulo() + " - " + livro.getDownloadCount() + livro.getIdioma()));
        System.out.println("Autores encontrados: ");
        autoresConvertidos.forEach(autor -> System.out.println(autor.getName()));
    }

    private List<DadosLivro> getDadosLivro() {
        System.out.println("Digite o nome do livro: ");
        String nomeLivro = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + "?search=" + nomeLivro.replace(" ", "%20"));
        OutsideObject outsideObject = conversor.obterDados(json, OutsideObject.class);
        List<DadosLivro> dadosLivros = outsideObject.getResults();
        return dadosLivros;
    }

    private List<DadosAutor> getDadosAutor(List<DadosLivro> livros) {
        List<DadosAutor> autores = livros.stream()
                .flatMap(dadosLivro -> dadosLivro.autores().stream())
                .collect(Collectors.toList());
        return autores;
    }

}
