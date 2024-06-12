package com.example.demo.repository;

import com.example.demo.model.Autor;
import com.example.demo.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Autor findByNameContainingIgnoreCase(String name);

    List<Autor> findByFalecimentoGreaterThan(int ano);
}

