package br.edu.ifto.minhabike.entity;

public class Servico {

    public String id;
    public String tipo_servico;
    public  String km_atual;
    public String acessorio;
    public  String conponente_troca;
    public  String descricao;
    public  String valor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo_servico() {
        return tipo_servico;
    }

    public void setTipo_servico(String tipo_servico) {
        this.tipo_servico = tipo_servico;
    }

    public String getKm_atual() {
        return km_atual;
    }

    public void setKm_atual(String km_atual) {
        this.km_atual = km_atual;
    }

    public String getAcessorio() {
        return acessorio;
    }

    public void setAcessorio(String acessorio) {
        this.acessorio = acessorio;
    }

    public String getConponente_troca() {
        return conponente_troca;
    }

    public void setConponente_troca(String conponente_troca) {
        this.conponente_troca = conponente_troca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
