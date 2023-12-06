<?php
require 'conecta_banco.php';
require 'valida_login.php';

// Verifica se houve erro na conexão
if (!$conexao) {
    die("Erro de conexão: " . mysqli_connect_error());
}

// Recebe os dados do formulário
$codigo = $_POST["codigo"];
$nome = $_POST["nome"];
$telefone = $_POST["telefone"];
$email = $_POST["email"];

// Insere os dados na tabela "tb_pecas"
$sql = "UPDATE tb_clientes SET nome = '$nome', telefone = '$telefone', email='$email' WHERE codigo = '$codigo'";


if (mysqli_query($conexao, $sql)) {
    echo "Dados alterados com sucesso!";
} else {
    echo "Erro ao alterar dados: " . mysqli_error($conexao);
}

// Fecha a conexão com o banco de dados
mysqli_close($conexao);
echo "<a href='manutencao.php'>[Volta para a página principal]</a>";
?>