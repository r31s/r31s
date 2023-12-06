package br.com.LuanBorges;

public class Alunos {
    public String nome;
    public double n1,n2,n3;

    public double calcularMedia(){
        return (n1+n2+n3)/3 ;

 }
    public String MostrarSituacao(double x ){
    if (x<7){
        return "Reprovado";
    } else {
        return "Aprovado";
    }
  }
    public  String toString(){
        return nome+", "+n1+", "+n2+", "+n3;
    }
 
}
    

