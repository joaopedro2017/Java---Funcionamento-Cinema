package cinema;

import java.util.ArrayList;
import java.util.Scanner;
import model.Filme;
import model.Sala;

/**
 *
 * @author JohnPeter
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        
        ArrayList<Filme> filmes;
        filmes = new ArrayList<>();
        
        ArrayList<Sala> salas;
        salas = new ArrayList<>();
        int i = 0, j = 0;
        
        System.out.println("------------------------------------------------------------------");
        System.out.println("\tInicializar as salas \n");
        String continua = "s";
        
        criarSalas(continua, i, teclado, salas);

        System.out.println("------------------------------------------------------------------");
        System.out.println("\tCadastro de Filmes\n");
        
        cadastrarFilme(teclado, filmes);
    }

    private static void cadastrarFilme(Scanner teclado, ArrayList<Filme> filmes) {
        String continua;
        do{
            Filme filme = new Filme();
            System.out.println("\t--------------------------------------");
            System.out.println("\tInforme o nome do Filme: "); filme.setNome(teclado.next());
            System.out.print("\tInforme o Tipo \"2D\" ou \"3D\": "); filme.setTipo(teclado.next());
            System.out.print("\tInforme a Duração em min: "); filme.setDuracao(teclado.nextInt());
            System.out.print("\tInforme a Classificação , ex(\"14\"): "); filme.setClassificacao(teclado.nextInt());
            System.out.print("\tO filme é dublado true ou false: "); filme.setDublado(teclado.nextBoolean());       
            
            filmes.add(filme);
            System.out.println("\tDeseja Cadastrar Novo Filme? (S)im (N)ão"); continua = teclado.next();            
        }while(continua.equalsIgnoreCase("s"));
    }

    private static void criarSalas(String continua, int i, Scanner teclado, ArrayList<Sala> salas) {
        while(continua.equalsIgnoreCase("s")){
            Sala sala = new Sala();
            i++;
            System.out.println("\t--------------------------------------");
            System.out.println("\tSala numero " + i); sala.setNumero(i);
            System.out.print("\tQuantidade de Fileiras: "); sala.setQntFileiras(teclado.nextInt());
            System.out.print("\tQuantidade de Colunas: "); sala.setQntColunas(teclado.nextInt());
            System.out.print("\tTipo de Som: "); sala.setTipoSom(teclado.next());
            
            sala.criarPoltronas();
            salas.add(sala);
            System.out.println("\tDeseja Cadastrar Nova Sala? (S)im (N)ão"); continua = teclado.next();            
        }
    }    
}
