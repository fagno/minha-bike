package br.edu.ifto.minhabike.entity;

public class Marcas {
   private String nome;

    public Marcas(String nome) {
        this.nome = nome;
    }

    public Marcas() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
