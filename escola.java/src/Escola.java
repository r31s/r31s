import java.util.Scanner;
import javax.print.attribute.standard.Media;
import classes.Aluno;

public class Escola {
    public static void main(String[] args) throws Exception {
       Scanner sc = new Scanner(System.in);
       Aluno a1 = new Aluno();

       System.out.println("digite o nome do aluno : " );
       a1.nome = sc.next();
       System.out.println("digite a nota 1 :");
       a1.n1 = sc.nextInt();
       System.out.println("digite a nota 2");
       a1.n2 = sc.nextInt();

       double media = a1.calcularMedia();
       String resultado = a1.MostrarSituacao(media);

       System.out.println("nome : " + a1.nome);
       System.out.println("media: " + media);
       System.out.println("resultado : " + resultado);
       sc.close();
    }
}