package model;

/*
 * @author JohnPeter
 */
public class Index {
    private int id;
    private boolean ocupada;

    public Index() {
    }

    public Index(int id, boolean ocupada) {
        this.id = id;
        this.ocupada = ocupada;
    }   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }    
}
