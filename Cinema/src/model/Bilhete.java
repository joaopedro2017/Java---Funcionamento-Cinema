package model;

/*
 * @author JohnPeter
 */
public class Bilhete {
    private Sessao sessao;
    private double valor;

    public Bilhete() {
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }    
}
