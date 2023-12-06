
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <title>Excluir Cliente</title>
  </head>
  <body>
    <?php
    require 'valida_login.php';


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
        echo "O cliente " .  $cliente['nome'] . " foi excluído";
        $sql = "DELETE FROM tb_clientes WHERE codigo='$codigo'";
        $resultado = mysqli_query($conexao, $sql);
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