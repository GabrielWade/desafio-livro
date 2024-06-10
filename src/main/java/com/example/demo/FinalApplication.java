package com.example.demo;

import com.example.demo.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinalApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FinalApplication.class, args);
	}

	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibirMenu();
	}
}
