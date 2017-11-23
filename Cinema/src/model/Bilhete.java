package model;

/*
 * @author JohnPeter
 */
public class Bilhete {
    private Sessao sessao;
    private double valorTotal;

    public Bilhete() {
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }  
    
}
