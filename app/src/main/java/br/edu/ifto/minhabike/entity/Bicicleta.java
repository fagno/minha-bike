package br.edu.ifto.minhabike.entity;

public class Bicicleta {
    private  int id;
    private String modelo;
    private String marca;
    private String tipo;
    private float peso;
    private String notas;

    public Bicicleta() {
    }

    public Bicicleta(int id, String modelo, String marca, String tipo, float peso, String notas) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.tipo = tipo;
        this.peso = peso;
        this.notas = notas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
