package model;

import java.util.ArrayList;

/*
 * @author JohnPeter
 */
public class Poltrona {
    private int fileira, coluna;
    private ArrayList<Index> indexs;

    public Poltrona(int fileira, int coluna) {
        this.fileira = fileira;
        this.coluna = coluna;
        indexs = new ArrayList<>();
    }    

    Poltrona() {        
    }

    public int getFileira() {
        return fileira;
    }

    public void setFileira(int fileira) {
        this.fileira = fileira;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }  

    public ArrayList<Index> getIndexs() {
        return indexs;
    }

    public void setIndexs(ArrayList<Index> indexs) {
        this.indexs = indexs;
    }

    public int retornoIdIndex(int id){
        int num = -1, cont = 0;
        for(Index i: indexs){
            if(i.getId() == id){
                num = cont;
            }
            cont++;
        }
        return num;
    }
}
