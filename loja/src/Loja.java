import java.util.Scanner;
import classes.Produto;

public class Loja {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        Produto p1 = new Produto();
        
       System.out.print("digite a descrição do produto : ");
       p1.descricao = sc.next();
       System.out.print("digite a quantidade do produto : ");
       p1.quantidade = sc.nextInt();
       System.out.print("digite o valor do produto : ");
       p1.valor = sc.nextDouble();

       System.out.println("descriçao do produto : " + p1.descricao);
       System.out.println("quantidade de "+p1.descricao +" e " + p1.quantidade);
       System.out.println("o valor da unidade de "+ p1.descricao +" e "+ p1.valor);
       System.out.println("valor do estoque de "+p1.descricao +" e "+ p1.calculavalor()); 

       p1.incluiitem(15);
       System.out.print("incluindo " + p1.quantidade +p1.descricao + " no estoque" );
       System.out.println("quantidade total no estoque e : " + p1.calculavalor()); 

       p1.excluiitem(5);
       System.out.print("excluindo " + p1.quantidade + " " +  p1.descricao +" "+ " no estoque");
       System.out.println("quantidade restante e "+" "+ p1.calculavalor()); 

       sc.close();

    }
}