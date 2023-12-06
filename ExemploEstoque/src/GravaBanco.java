import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GravaBanco {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/exemplo_estoque";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        int linhasInseridas = importarEstoque();
        System.out.println("Foram inseridas " + linhasInseridas + " linhas na tabela produtos.");
    }

    private static int importarEstoque() {
        int linhasInseridas = 0;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO produtos (codigo, nome, preco, quantidade,autor) VALUES (?, ?, ?, ?)");
             BufferedReader reader = new BufferedReader(new FileReader("estoque.txt"))) {

            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                int codigo = Integer.parseInt(partes[0]);
                String nome = partes[1];
                double preco = Double.parseDouble(partes[2]);
                int quantidade = Integer.parseInt(partes[3]);
                String autor = partes[4];

                statement.setInt(1, codigo);
                statement.setString(2, nome);
                statement.setDouble(3, preco);
                statement.setInt(4, quantidade);

                statement.executeUpdate();
                linhasInseridas++;
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados na tabela produtos: " + e.getMessage());
        }

        return linhasInseridas;
    }
}