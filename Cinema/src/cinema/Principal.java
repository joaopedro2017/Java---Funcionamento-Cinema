package cinema;

import java.util.ArrayList;
import java.util.Scanner;
import model.Filme;
import model.Sala;
import model.Sessao;

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
        String continua = "s";
        
        System.out.println("------------------------------------------------------------------");
        System.out.println("\tInicializar as salas \n");                
        criarSalas(continua, i, teclado, salas);

        System.out.println("------------------------------------------------------------------");
        System.out.println("\tCadastro de Filmes\n");        
        cadastrarFilmes(teclado, filmes);
        
        System.out.println("------------------------------------------------------------------");
        System.out.println("\tMontar Sessoẽs\n");
        montarSessoes(j, teclado, filmes, salas);
    }

    private static void montarSessoes(int j, Scanner teclado, ArrayList<Filme> filmes, ArrayList<Sala> salas) {
        String continua;
        do{
            Sessao sessao = new Sessao();
            int idx = 0, filmSel = 0, salSel = 0;
            boolean valido = false;
            j++;
            System.out.println("\t--------------------------------------");
            System.out.println("\tSessao : "+j); sessao.setId(j);
            System.out.print("\tInforme o Preço: "); sessao.setPreco(teclado.nextDouble());
            System.out.print("\tInforme a Hora de Início: "); sessao.setHora(teclado.nextInt());
            System.out.print("\tInforme o Minuto de Início: "); sessao.setMin(teclado.nextInt());
            System.out.print("\n");
            
            System.out.println("\tSelecione o Filme: ");
            for(Filme f: filmes){
                System.out.println("\t"+(++idx)+ ") "+f.getNome());                
            }
            System.out.println("\tDigite o Número do Filme: "); filmSel = teclado.nextInt();
            
            sessao.setFilme(filmes.get(filmSel-1));
            filmes.get(filmSel-1).getSessoes().add(sessao);            
            
            System.out.print("\n");
            System.out.println("\tEscolha a Sala: ");
            for(Sala s: salas){
                if(!s.verificarSessao(sessao)){
                    System.out.println("\tSala "+s.getNumero());
                    valido = true;
                }
            }
            
            if(valido){
                System.out.println("\tDigite o Número da Sala: "); salSel = teclado.nextInt();          
                sessao.setSala(salas.get(salSel-1));
                salas.get(salSel-1).getSessoes().add(sessao);
                System.out.println("\tDeseja Montar Nova Sessão? (S)im (N)ão"); continua = teclado.next();
            }else{
                sessao.setId(0);
                sessao.setFilme(null);
                filmes.get(filmSel-1).getSessoes().remove(sessao);
                j--;
                System.out.println("\tNão tem Sala Disponível para essa Sessão"); 
                System.out.println("\tDeseja tentar Novamente? (S)im (N)ão"); continua = teclado.next();
            }           
        }while(continua.equalsIgnoreCase("s"));
    }

    private static void cadastrarFilmes(Scanner teclado, ArrayList<Filme> filmes) {
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
