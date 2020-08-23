package br.edu.ifto.minhabike.entity;

public class Servico {

    private String tipo;
    private String componenteTroca;
    private String acessorio;
    private String descricao;
    private String kmAtual;
    private String data;
    private String valor;

    public Servico() {
    }

    public Servico(String tipo, String componenteTroca, String acessorio, String descricao,
                   String kmAtual, String data, String valor) {
        this.tipo = tipo;
        this.componenteTroca = componenteTroca;
        this.acessorio = acessorio;
        this.descricao = descricao;
        this.kmAtual = kmAtual;
        this.data = data;
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getComponenteTroca() {
        return componenteTroca;
    }

    public void setComponenteTroca(String componenteTroca) { this.componenteTroca = componenteTroca; }

    public String getAcessorio() {
        return acessorio;
    }

    public void setAcessorio(String acessorio) {
        this.acessorio = acessorio;
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
