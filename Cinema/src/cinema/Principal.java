package cinema;

import java.util.ArrayList;
import java.util.Scanner;
import model.Bilhete;
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
        cadastroCinema(continua, i, teclado, salas, filmes, j);
        
        inicializarBilheteria(salas, teclado);
        
        valorTotalFilme(filmes);
        valorTotalSessao(salas);
        valorTotalSala(salas);        
    }  

    private static void inicializarBilheteria(ArrayList<Sala> salas, Scanner teclado) {
        String continua;
        System.out.println("------------------------------------------------------------------");
        System.out.print("\n\n");
        System.out.println("\t\tBilheteria");
        do{
            System.out.println("\n");
            System.out.println("\tSelecione a Sessão: ");
            listarSessoes(salas);
            
            continua = selecioneSessao(teclado, salas);           
        }while(continua.equalsIgnoreCase("s"));
    }  

    private static String selecioneSessao(Scanner teclado, ArrayList<Sala> salas) {
        String continua;
        int numSala = 0, numSessao = 0;
        System.out.println("\tDigite o código da Sessão: ");
        int id = teclado.nextInt();
        for(Sala s: salas){
            int cont = 0;
            for(Sessao e: s.getSessoes()){
                if(e.getId() == id){
                    numSala = e.getSala().getNumero();
                    numSessao = cont;
                }
                cont++;
            }
        }
        continua = quantificarVendaBilhete(salas, numSala, id, teclado, numSessao);
        return continua;
    }

    private static String quantificarVendaBilhete(ArrayList<Sala> salas, int numSala, int id, Scanner teclado, int numSessao) {
        String continua;
        int end = salas.get(numSala-1).getPoltronas().get(0).retornoIdIndex(id);
        salas.get(numSala-1).mapaSala(end);
        System.out.println("\t------Quantos lugares?--------");
        int qnt = teclado.nextInt();
        int desc = qnt;
        venderBilhete(qnt, teclado, salas, numSala, numSessao, end, desc);
        System.out.println("\tDeseja Vender Novo Ingresso? (S)im (N)ão");
        continua = teclado.next();
        return continua;
    }

    private static void venderBilhete(int qnt, Scanner teclado, ArrayList<Sala> salas, int numSala, int numSessao, int end, int desc) {
        int fil, col, aux = 1;
        double valorTotal = 0;
        while(qnt > 0){
            System.out.println("\t"+(aux++) +"º Ingresso: ");
            System.out.print("\tDigite a fileira: "); fil = teclado.nextInt();
            System.out.print("\tDigite a coluna: "); col = teclado.nextInt();
            System.out.print("\n");
            boolean valido = salas.get(numSala-1).getSessoes().get(numSessao).escolherLugar(fil, col, end);
            if(valido){
                Bilhete bilhete = criarBilhete(salas, numSala, numSessao, desc);
                imprimirBilhete(bilhete, fil, col);
                valorTotal += valorBilhete(salas, numSala, numSessao, desc);
            }else{
                qnt++;
                aux = mensagemLugarOcupado(aux);
            }
            qnt--;
        }
        System.out.println("\n\tValor Total: R$ " + valorTotal);
        System.out.println("------------------------------------------------------------------");
    }

    private static int mensagemLugarOcupado(int aux) {
        aux--;
        System.out.println("\tLugar já ocupado ou não existe");
        System.out.println("\tTente Novamente!");
        System.out.println("------------------------------------------------------------------");
        return aux;
    }
   
    private static void valorTotalSessao(ArrayList<Sala> salas) {
        System.out.print("------------------------------------------------------------------");
        System.out.print("\n");
        System.out.println("\tValor por Sessão");
        for(Sala s: salas){
            for(Sessao e: s.getSessoes()){
                System.out.println("\tSessao: "+e.getId()+" - Valor Total de Vendas: R$ "+e.valorTotalBilhetesVendidos());
            }            
        }
    }  

    private static void valorTotalFilme(ArrayList<Filme> filmes) {
        System.out.print("------------------------------------------------------------------");
        System.out.print("\n");
        System.out.println("\tValor por Filme");
        for(Filme f: filmes){
            System.out.println("\tFilme: "+f.getNome()+" - Valor Total de Vendas: R$ "+f.valorTotalFilme());            
        }
    }  

    private static void valorTotalSala(ArrayList<Sala> salas) {
        System.out.print("------------------------------------------------------------------");
        System.out.print("\n");
        System.out.println("\tValor por Sala");
        double valorTotal = 0;
        for(Sala s: salas){            
            System.out.println("\tSala "+s.getNumero()+" - Valor Total de Vendas: R$ "+s.valorTotalSala());
            valorTotal += s.valorTotalSala();
        }
        valorTotalCinema(valorTotal);
    }  

    private static void valorTotalCinema(double valorTotal) {
        System.out.print("------------------------------------------------------------------");
        System.out.print("\n");
        System.out.println("\tValor Total de Vendas: R$ " + valorTotal);
    }  

    private static Bilhete criarBilhete(ArrayList<Sala> salas, int numSala, int numSessao, int desc) {
        double valorBilhete = valorBilhete(salas, numSala, numSessao, desc);
        Bilhete bilhete = new Bilhete();
        bilhete.setValor( valorBilhete );
        bilhete.setSessao( salas.get(numSala-1).getSessoes().get(numSessao) );
        salas.get(numSala-1).getSessoes().get(numSessao).getBilhetes().add(bilhete);
        return bilhete;
    }

    private static double valorBilhete(ArrayList<Sala> salas, int numSala, int numSessao, int desc) {
        double valorSessao = salas.get(numSala-1).getSessoes().get(numSessao).getPreco();
        double valorBilhete = desc > 4? valorSessao * 0.85: valorSessao;
        return valorBilhete;
    }

    private static void imprimirBilhete(Bilhete bilhete, int fil, int col) {
        String dub = "";
        if(bilhete.getSessao().getFilme().isDublado()){
            dub = "Dublado";
        }else{
            dub = "Legendado";
        }
        System.out.println("\t\tCinemania");
        System.out.println("\tFil: " +fil+", Col: "+col);
        System.out.println("\tSala: "+bilhete.getSessao().getSala().getNumero());
        System.out.println("\tFilme: "+bilhete.getSessao().getFilme().getNome() +" - "+bilhete.getSessao().getFilme().getTipo() + " - " +dub) ;
        System.out.println("\tClassificação: " + bilhete.getSessao().getFilme().getClassificacao());
        System.out.println("\tSessão: "+bilhete.getSessao().getHora()+":"+bilhete.getSessao().getMin()+" - "+bilhete.getSessao().horaFinal()+":"+bilhete.getSessao().minFinal());
        System.out.println("\tValor: R$ " + bilhete.getValor());
        System.out.println("------------------------------------------------------------------");
    }

    private static void cadastroCinema(String continua, int i, Scanner teclado, ArrayList<Sala> salas, ArrayList<Filme> filmes, int j) {
        System.out.println("------------------------------------------------------------------");
        System.out.println("\tInicializar as salas \n");
        criarSalas(continua, i, teclado, salas);
        
        System.out.println("------------------------------------------------------------------");
        System.out.println("\tCadastro de Filmes\n");
        cadastrarFilmes(teclado, filmes);
        
        System.out.println("------------------------------------------------------------------");
        System.out.println("\tMontar Sessoẽs\n");
        montarSessoes(j, teclado, filmes, salas);
        
        indexarPoltronas(salas);
    }

    private static void listarSessoes(ArrayList<Sala> salas) {
        for(Sala s: salas){
            for(Sessao e: s.getSessoes()){
                System.out.println("\t"+e.toString());
            }
        }
    }

    private static void indexarPoltronas(ArrayList<Sala> salas) {
        for(Sala s: salas){
            s.criarPoltronas();
            s.indexarPoltronas();
        }
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
            
            filmSel = escolherFilme(filmes, idx, teclado, sessao);            
            valido = listarSalasValidas(salas, sessao, valido);            
            if(valido){
                continua = escolherSala(teclado, sessao, salas);             
            }else{
                j = removeFilmeSessao(sessao, filmes, filmSel, j);
                continua = mensagemSalaIndisponivel(teclado);
            }           
        }while(continua.equalsIgnoreCase("s"));
    }

    private static String mensagemSalaIndisponivel(Scanner teclado) {
        String continua;
        System.out.println("\tNão tem Sala Disponível para essa Sessão");
        System.out.println("\tDeseja tentar Novamente? (S)im (N)ão");
        continua = teclado.next();
        return continua;
    }

    private static int removeFilmeSessao(Sessao sessao, ArrayList<Filme> filmes, int filmSel, int j) {
        sessao.setId(0);
        sessao.setFilme(null);
        filmes.get(filmSel-1).getSessoes().remove(sessao);
        j--;
        return j;
    }

    private static String escolherSala(Scanner teclado, Sessao sessao, ArrayList<Sala> salas) {
        int salSel;
        String continua;
        System.out.println("\tDigite o Número da Sala: ");
        salSel = teclado.nextInt();
        sessao.setSala(salas.get(salSel-1));
        salas.get(salSel-1).getSessoes().add(sessao);
        System.out.println("\tDeseja Montar Nova Sessão? (S)im (N)ão");
        continua = teclado.next();
        return continua;
    }

    private static boolean listarSalasValidas(ArrayList<Sala> salas, Sessao sessao, boolean valido) {
        System.out.print("\n");
        System.out.println("\tEscolha a Sala: ");
        for(Sala s: salas){
            if(!s.verificarSessao(sessao)){
                System.out.println("\tSala "+s.getNumero());
                valido = true;
            }
        }
        return valido;
    }

    private static int escolherFilme(ArrayList<Filme> filmes, int idx, Scanner teclado, Sessao sessao) {
        int filmSel;
        System.out.println("\tSelecione o Filme: ");
        for(Filme f: filmes){
            System.out.println("\t"+(++idx)+ ") "+f.getNome());
        }
        System.out.println("\tDigite o Número do Filme: ");
        filmSel = teclado.nextInt();
        sessao.setFilme(filmes.get(filmSel-1));
        filmes.get(filmSel-1).getSessoes().add(sessao);
        return filmSel;
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
            
            salas.add(sala);
            System.out.println("\tDeseja Cadastrar Nova Sala? (S)im (N)ão"); continua = teclado.next();            
        }
    }    
}