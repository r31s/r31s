import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EstoqueBanco {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/exemplo_1estoque";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        List<Produto> estoque = lerEstoqueDoBanco();
        exibirEstoque(estoque);

        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            exibirMenu();
            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    Produto novoProduto = criarNovoProduto(scanner);
                    adicionarProduto(estoque, novoProduto);
                    break;
                case 2:
                    System.out.print("Digite o código do produto a ser atualizado: ");
                    int codigo = scanner.nextInt();
                    scanner.nextLine();
                    atualizarProduto(estoque, codigo, scanner);
                    break;
                case 3:
                    exibirMenuFerramentas(scanner, estoque);
                    break;
                case 4:
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }

            exibirEstoque(estoque);
        }

        salvarEstoqueNoBanco(estoque);
        scanner.close();
    }

    private static List<Produto> lerEstoqueDoBanco() {
        List<Produto> estoque = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM produtos");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int codigo = resultSet.getInt("codigo");
                String nome = resultSet.getString("nome");
                double preco = resultSet.getDouble("preco");
                int quantidade = resultSet.getInt("quantidade");
                String autor = resultSet.getString("autor");

                if (autor != null) {
                    Livro livro = new Livro(codigo, nome, preco, quantidade, autor);
                    estoque.add(livro);
                } else {
                    Produto produto = new Produto(codigo, nome, preco, quantidade);
                    estoque.add(produto);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao ler o estoque do banco de dados: " + e.getMessage());
        }

        return estoque;
    }

    private static void exibirEstoque(List<Produto> estoque) {
        System.out.println("\nEstoque:");
        if (estoque.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
        } else {
            for (Produto produto : estoque) {
                System.out.println(produto);
            }
        }
    }

    private static Produto criarNovoProduto(Scanner scanner) {
        System.out.println("Escolha o tipo de produto:");
        System.out.println("1. Produto");
        System.out.println("2. Livro");

        int escolha = scanner.nextInt();
        scanner.nextLine();

        switch (escolha) {
            case 1:
                System.out.print("Digite o código do produto: ");
                int codigo = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Digite o nome do produto: ");
                String nome = scanner.nextLine();

                System.out.print("Digite o preço do produto: ");
                double preco = scanner.nextDouble();
                scanner.nextLine();

                System.out.print("Digite a quantidade do produto: ");
                int quantidade = scanner.nextInt();
                scanner.nextLine();

                return new Produto(codigo, nome, preco, quantidade);
            case 2:
                System.out.print("Digite o código do livro: ");
                codigo = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Digite o nome do livro: ");
                nome = scanner.nextLine();

                System.out.print("Digite o preço do livro: ");
                preco = scanner.nextDouble();
                scanner.nextLine();

                System.out.print("Digite a quantidade do livro: ");
                quantidade = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Digite o autor do livro: ");
                String autor = scanner.nextLine();

                return new Livro(codigo, nome, preco, quantidade, autor);
            default:
                System.out.println("Opção inválida. Criando um produto padrão.");
                return new Produto(0, "Produto Padrão", 0.0, 0);
        }
    }

    private static void adicionarProduto(List<Produto> estoque, Produto novoProduto) {
        estoque.add(novoProduto);
        System.out.println("Produto adicionado com sucesso.");
    }

    private static void atualizarProduto(List<Produto> estoque, int codigo, Scanner scanner) {
        Produto produtoAtualizado = null;

        for (Produto produto : estoque) {
            if (produto.getCodigo() == codigo) {
                produtoAtualizado = produto;
                break;
            }
        }

        if (produtoAtualizado != null) {
            System.out.print("Digite o novo nome do produto: ");
            String nome = scanner.nextLine();
            produtoAtualizado.setNome(nome);

            System.out.print("Digite o novo preço do produto: ");
            double preco = scanner.nextDouble();
            produtoAtualizado.setPreco(preco);

            System.out.print("Digite a nova quantidade do produto: ");
            int quantidade = scanner.nextInt();
            produtoAtualizado.setQuantidade(quantidade);

            System.out.println("Produto atualizado com sucesso.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static void salvarEstoqueNoBanco(List<Produto> estoque) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM produtos");
             PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO produtos (codigo, nome, preco, quantidade, autor) VALUES (?, ?, ?, ?, ?)")) {

            // Limpa a tabela de produtos
            deleteStatement.executeUpdate();

            // Insere os produtos no banco de dados
            for (Produto produto : estoque) {
                if (produto instanceof Livro) {
                    Livro livro = (Livro) produto;
                    insertStatement.setInt(1, produto.getCodigo());
                    insertStatement.setString(2, produto.getNome());
                    insertStatement.setDouble(3, produto.getPreco());
                    insertStatement.setInt(4, produto.getQuantidade());
                    insertStatement.setString(5, livro.getAutor());
                } else {
                    insertStatement.setInt(1, produto.getCodigo());
                    insertStatement.setString(2, produto.getNome());
                    insertStatement.setDouble(3, produto.getPreco());
                    insertStatement.setInt(4, produto.getQuantidade());
                    insertStatement.setString(5, null);
                }
                insertStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar o estoque no banco de dados: " + e.getMessage());
        }
    }

    private static void exibirMenu() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1. Adicionar novo produto");
        System.out.println("2. Atualizar produto existente");
        System.out.println("3. Ferramentas");
        System.out.println("4. Sair");
    }

    private static void exibirMenuFerramentas(Scanner scanner, List<Produto> estoque) {
        boolean executando = true;

        while (executando) {
            System.out.println("\nMenu de Ferramentas:");
            System.out.println("1. Ler arquivo");
            System.out.println("2. Gravar arquivo");
            System.out.println("3. Ler banco de dados");
            System.out.println("4. Gravar banco de dados");
            System.out.println("5. Voltar ao menu principal");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    lerDoArquivo();
                    break;
                case 2:
                    salvarNoArquivo(estoque);
                    break;
                case 3:
                    estoque = lerEstoqueDoBanco();
                    break;
                case 4:
                    salvarEstoqueNoBanco(estoque);
                    break;
                case 5:
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    private static void lerDoArquivo() {
        try (Scanner scanner = new Scanner(System.in);
             Scanner fileScanner = new Scanner(new FileReader("estoque.txt"))) {
            System.out.println("\nConteúdo do arquivo:");
            while (fileScanner.hasNextLine()) {
                String linha = fileScanner.nextLine();
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    private static void salvarNoArquivo(List<Produto> estoque) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("estoque.txt"))) {
            for (Produto produto : estoque) {
                if (produto instanceof Livro) {
                    Livro livro = (Livro) produto;
                    writer.write(produto.getCodigo() + "," + produto.getNome() + "," +
                            produto.getPreco() + "," + produto.getQuantidade() + "," +
                            livro.getAutor());
                } else {
                    writer.write(produto.getCodigo() + "," + produto.getNome() + "," +
                            produto.getPreco() + "," + produto.getQuantidade());
                }
                writer.newLine();
            }
            System.out.println("Estoque salvo no arquivo com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }
}

class Produto {
    private int codigo;
    private String nome;
    private double preco;
    private int quantidade;

    public Produto(int codigo, String nome, double preco, int quantidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                '}';
    }
}

class Livro extends Produto {
    private String autor;

    public Livro(int codigo, String nome, double preco, int quantidade, String autor) {
        super(codigo, nome, preco, quantidade);
        this.autor = autor;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "codigo=" + getCodigo() +
                ", nome='" + getNome() + '\'' +
                ", preco=" + getPreco() +
                ", quantidade=" + getQuantidade() +
                ", autor='" + autor + '\'' +
                '}';
    }
}

/**
    CREATE DATABASE IF NOT EXISTS estoque;
    USE estoque;
    CREATE TABLE IF NOT EXISTS produtos (
    codigo INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    quantidade INT NOT NULL,
    autor VARCHAR(100)
);
 */
