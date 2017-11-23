package model;

import java.util.ArrayList;

/*
 * @author JohnPeter
 */
public class Sessao {
    private int id, hora, min;
    private double preco;
    private Filme filme;
    private Sala sala;
    private ArrayList<Bilhete> bilhetes;

    public Sessao(int id, double preco, Filme filme) {
        this.id = id;
        this.preco = preco;
        this.filme = filme;
        bilhetes = new ArrayList<>();
    }  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        if(hora >= 0 && hora < 24){
            this.hora = hora;
        }
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        if(min >= 0 && min < 60){
            this.min = min;
        }
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public ArrayList<Bilhete> getBilhetes() {
        return bilhetes;
    }

    public void setBilhetes(ArrayList<Bilhete> bilhetes) {
        this.bilhetes = bilhetes;
    }  
    
}
