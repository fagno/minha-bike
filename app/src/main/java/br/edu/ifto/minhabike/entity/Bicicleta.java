package br.edu.ifto.minhabike.entity;

public class Bicicleta {
    private String nome;
    private String modelo;
    private String marca;
    private String tipo;
    private float peso;
    private String notas;

    public Bicicleta() {
    }

    public Bicicleta( String nome, String modelo, String marca, String tipo, float peso, String notas) {
        this.nome = nome;
        this.modelo = modelo;
        this.marca = marca;
        this.tipo = tipo;
        this.peso = peso;
        this.notas = notas;
    }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}
