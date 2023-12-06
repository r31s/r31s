<?php
require 'valida_login.php';
?>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <title>Edita Cliente</title>
  </head>
  <body>
    <?php
    // Verifica se o código do cliente foi passado via GET
    if (isset($_GET['codigo'])) {
      $codigo = $_GET['codigo'];

      // Conexão com o banco de dados
      require 'conecta_banco.php';

      // Consulta na tabela de tb_clientes
      $sql = "SELECT * FROM tb_clientes WHERE codigo='$codigo'";
      $resultado = mysqli_query($conexao, $sql);

      // Verifica se o produto foi encontrado
      if (mysqli_num_rows($resultado) > 0) {
        $cliente = mysqli_fetch_array($resultado);
    ?>
    <h2>Editar Cliente</h2>
    <form action="salva_cliente.php" method="post">
      <input type="hidden" name="codigo" value="<?php echo $cliente['codigo']; ?>">
      <label for="nome">Nome:</label>
      <input type="text" name="nome" id="nome" value="<?php echo $cliente['nome']; ?>"><br><br>
      <label for="telefone">Telefone:</label>
      <input type="text" name="telefone" id="telefone" value="<?php echo $cliente['telefone']; ?>"><br><br>
      <label for="email">e-mail:</label>
      <input type="email" name="email" id="email" value="<?php echo $cliente['email']; ?>"><br><br>
      <input type="submit" value="Salvar">
    </form>
    <?php
      } else {
        echo "Cliente não encontrado.";
      }

      // Fechar a conexão com o banco de dados
      mysqli_close($conexao);
    } else {
      echo "Código do cliente não informado.";
    }
    ?>
    <a href="manutencao.php">[Volta para a página principal]</a>
  </body>
</html>