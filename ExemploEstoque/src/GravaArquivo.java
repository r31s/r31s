import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GravaArquivo {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/exemplo_estoque";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        exportarEstoque();
        System.out.println("Os dados foram exportados para o arquivo estoque.txt.");
    }

    private static void exportarEstoque() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM produtos");
             ResultSet resultSet = statement.executeQuery();
             BufferedWriter writer = new BufferedWriter(new FileWriter("estoque.txt"))) {

            while (resultSet.next()) {
                int codigo = resultSet.getInt("codigo");
                String nome = resultSet.getString("nome");
                double preco = resultSet.getDouble("preco");
                int quantidade = resultSet.getInt("quantidade");

                String linha = codigo + "," + nome + "," + preco + "," + quantidade;
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao gravar no arquivo: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao ler dados da tabela produtos: " + e.getMessage());
        }
    }
}
