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

    public ArrayList<Sessao> getSessoes() {
        return sessoes;
    }

    public void setSessoes(ArrayList<Sessao> sessoes) {
        this.sessoes = sessoes;
    }

    public ArrayList<Poltrona> getPoltronas() {
        return poltronas;
    }

    public void setPoltronas(ArrayList<Poltrona> poltronas) {
        this.poltronas = poltronas;
    }
    
    public boolean verificarSessao(Sessao sessao){
        boolean exist = true;
                
        if( sessoes.isEmpty()){
            exist = false;
        }else{
            for(Sessao s: sessoes){
                if( !sessaoAnterior(sessao, s)  || !sessaoPosterior(sessao, s) ){
                    exist = false;
                }
            }
        }
        return exist;
    }

    private static boolean sessaoPosterior(Sessao sessao, Sessao s) {
        return (sessao.getHora() > s.horaFinal()) || ((sessao.getHora() == s.horaFinal()) && (sessao.getMin() >= s.minFinal()));
    }

    private static boolean sessaoAnterior(Sessao sessao, Sessao s) {
        return (sessao.horaFinal() < s.getHora()) || ((sessao.horaFinal() == s.getHora()) && (sessao.minFinal() <= s.getMin()));
    }

    public void criarPoltronas(){
        for(int i = 0; i < qntFileiras; i++){
            for(int j = 0; j < qntColunas; j++){
                Poltrona poltrona = new Poltrona();
                poltrona.setFileira(i);
                poltrona.setColuna(j);
                poltronas.add(poltrona);
            }
        }
    }
    
    public void indexarPoltronas(){
        for(Poltrona p: poltronas){
            for(Sessao s: sessoes){
                Index index = new Index();
                index.setId(s.getId());
                index.setOcupada(false);
                p.getIndexs().add(index);
            }
        }
    }
    
    public boolean poltronaOcupada(int f, int c, int end){
        return getPoltronas().get(indexPoltronaFC(f, c)).getIndexs().get(end).isOcupada();
    }

    public int indexPoltronaFC(int f, int c) {
        return ((f * qntColunas) + c);
    }
}
