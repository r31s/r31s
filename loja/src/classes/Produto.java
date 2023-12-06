package classes;

   public class Produto {
   public String descricao;
   public int quantidade;
   public double valor;

    public double calculavalor(){
       return quantidade * valor;
    }

    public void incluiitem(int x){
        quantidade += x;
    }
    public void excluiitem(int x){
        quantidade -= x;
    }


}