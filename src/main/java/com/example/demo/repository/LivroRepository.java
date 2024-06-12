package com.example.demo.repository;

import com.example.demo.model.Livro;
import com.example.demo.model.Traducao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByidioma(Traducao idioma);
}
