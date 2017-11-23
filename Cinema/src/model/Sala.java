package model;

import java.util.ArrayList;

/*
 * @author JohnPeter
 */
public class Sala {
    private int numero, qntFileiras, qntColunas;
    private String tipoSom;
    private ArrayList<Sessao> sessoes;
    private ArrayList<Poltrona> poltronas;

    public Sala(int numero, int qntFileiras, int qntColunas, String tipoSom) {
        this.numero = numero;
        this.qntFileiras = qntFileiras;
        this.qntColunas = qntColunas;
        this.tipoSom = tipoSom;
        sessoes = new ArrayList<>();
        poltronas = new ArrayList<>();
    }  

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getQntFileiras() {
        return qntFileiras;
    }

    public void setQntFileiras(int qntFileiras) {
        this.qntFileiras = qntFileiras;
    }

    public int getQntColunas() {
        return qntColunas;
    }

    public void setQntColunas(int qntColunas) {
        this.qntColunas = qntColunas;
    }

    public String getTipoSom() {
        return tipoSom;
    }

    public void setTipoSom(String tipoSom) {
        this.tipoSom = tipoSom;
    }   
    
}
