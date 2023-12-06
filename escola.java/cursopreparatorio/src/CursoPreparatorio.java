import java.util.Scanner;

import br.com.LuanBorges.Alunos;
import br.com.LuanBorges.Professor;
import br.com.LuanBorges.Turma;

public class CursoPreparatorio {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Alunos a1 = new Alunos();
        Professor p1 = new Professor();
        Turma t1 = new Turma();        
        
        System.out.println("digite o nome do professor : ");
        p1.nome = sc.next();
        System.out.println("digite a idade do "+ p1.nome+" : ");
        p1.idade = sc.nextInt();
        System.out.println("digite a materia do "+p1.nome+ " : ");
        p1.materia = sc.next();
        System.out.println("digite o salario do "+p1.nome+ " : ");
        p1.salario = sc.nextDouble();

        System.out.println("digite o codigo da turma : ");
        t1.codigo = sc.next();
        System.out.println("descrição : ");
        t1.descricao = sc.next();
        System.out.println("digite a sala : ");
        t1.sala = sc.next();

        System.out.println("digite o nome do aluno : ");
        a1.nome = sc.next();
        System.out.println("digite a nota 1 : ");
        a1.n1 = sc.nextDouble();
        System.out.println("digite a nota 2 : ");
        a1.n2 = sc.nextDouble();
        System.out.println("digite a nota 3 : ");
        a1.n3 = sc.nextDouble();

        double media = a1.calcularMedia();
        String resultado = a1.MostrarSituacao(media);

        System.out.println(resultado);
        System.out.println(media);

        System.out.println(a1);
        sc.close();
    }
}
