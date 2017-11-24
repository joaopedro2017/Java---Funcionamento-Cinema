package model;

import java.util.ArrayList;

/*
 * @author JohnPeter
 */
public class Filme {
    private String nome, tipo;
    private int duracao, classificacao;
    private boolean dublado;
    private ArrayList<Sessao> sessoes;

    public Filme(String nome, String tipo, int duracao, int classificacao, boolean dublado) {
        this.nome = nome;
        this.tipo = tipo;
        this.duracao = duracao;
        this.classificacao = classificacao;
        this.dublado = dublado;
        sessoes = new ArrayList<>();
    } 

    public Filme() {
        this.nome = "";
        this.tipo = "";
        this.duracao = 0;
        this.classificacao = 0;
        this.dublado = false;
        sessoes = new ArrayList<>();
    }  

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    public boolean isDublado() {
        return dublado;
    }

    public void setDublado(boolean dublado) {
        this.dublado = dublado;
    }

    public ArrayList<Sessao> getSessoes() {
        return sessoes;
    }

    public void setSessoes(ArrayList<Sessao> sessoes) {
        this.sessoes = sessoes;
    }
    
    public double valorTotalFilme(){
        double valorTotal = 0;
        for(Sessao s: sessoes){
            valorTotal += s.valorTotalBilhetesVendidos();
        }
        return valorTotal;
    }
}
