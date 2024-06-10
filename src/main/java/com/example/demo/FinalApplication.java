package com.example.demo;

import com.example.demo.model.Livro;
import com.example.demo.principal.Principal;
import com.example.demo.repository.AutorRepository;
import com.example.demo.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinalApplication implements CommandLineRunner {
	@Autowired
	private LivroRepository repositorio;

	@Autowired
	private AutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(FinalApplication.class, args);
	}

	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorio, autorRepository);
		principal.exibirMenu();
	}
}
