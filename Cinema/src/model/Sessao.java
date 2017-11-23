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
    
    public int horaFinal(){
        return (hora + duracaoHora());
    }
    
    public int minFinal(){
        return ((filme.getDuracao() + min + 10) - (duracaoHora() * 60));
    }

    private int duracaoHora() {
        return ((filme.getDuracao() + min + 10) / 60);
    }
    
    public boolean escolherLugar(int f, int c, int end){
        if(sala.getQntFileiras() > f && sala.getQntColunas() > c && !sala.poltronaOcupada(f, c, end)){
            sala.getPoltronas().get( sala.indexPoltronaFC(f, c) ).getIndexs().get(end).setOcupada(true);
            return true;
        }else{
            return false;
        }        
    }
    
    public double valorTotalBilhetesVendidos(){
        double valorTotal = 0;
        for(Bilhete b: bilhetes){
            valorTotal += b.getValorTotal();
        }
        return valorTotal;
    }
    
    @Override
    public String toString(){
        return ("Sessão "+id+" : "+hora+":"+min+" - "+horaFinal()+":"+minFinal()+" Filme: " +filme.getNome());
    }
}