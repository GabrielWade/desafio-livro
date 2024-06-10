package com.example.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nascimento;
    private String falecimento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livro;

    public Autor(DadosAutor dadosAutor) {
        this.name = dadosAutor.nome();
        this.nascimento = dadosAutor.nascimento();
        this.falecimento = dadosAutor.falecimento();
    }

    public Autor() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getFalecimento() {
        return falecimento;
    }

    public void setFalecimento(String falecimento) {
        this.falecimento = falecimento;
    }
}
