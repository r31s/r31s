package classes;

public class Aluno {
    public String nome;
    public double n1,n2;

    public double calcularMedia(){
            return (n1+n2)/2;
    }
    public String MostrarSituacao(double x ){
        if (x<7){
            return "Reprovado";
        } else {
            return "Aprovado";
        }
    }
}
