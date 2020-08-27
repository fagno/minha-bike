package br.edu.ifto.minhabike.entity;

import android.icu.util.LocaleData;

import java.time.LocalDate;

public class Servico {

    private String bikeNome;
    private String bikeServico;
    private String tipo;
    private String descricao;
    private String kmAtual;
    private String data;
    private String valor;

    public Servico() {
    }

    public Servico(String bikeNome,String bikeServico, String tipo, String descricao,
                   String kmAtual, String data, String valor) {
        this.bikeNome = bikeNome;
        this.bikeServico = bikeServico;
        this.tipo = tipo;
        this.descricao = descricao;
        this.kmAtual = kmAtual;
        this.data = data;
        this.valor = valor;
    }


    public String getBikeNome() {
        return bikeNome;
    }

    public void setBikeNome(String bikeNome) {
        this.bikeNome = bikeNome;
    }

    public String getBikeServico() {
        return bikeServico;
    }

    public void setBikeServico(String bikeServico) {
        this.bikeServico = bikeServico;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getKmAtual() {
        return kmAtual;
    }

    public void setKmAtual(String kmAtual) {
        this.kmAtual = kmAtual;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }


}
