<?php
require 'valida_login.php';
require 'conecta_banco.php'; //conecta com o banco de dados :)

// Verifica se houve erro na conexão
if (!$conexao) {
    die("Erro de conexão: " . mysqli_connect_error());
}

// Recebe os dados do formulário
$nome = $_POST["nome"];
$telefone = $_POST["telefone"];
$email = $_POST["email"];

// Insere os dados na tabela "tb_clientes"
$sql = "INSERT INTO tb_clientes (nome, telefone, email) VALUES ('$nome', '$telefone', '$email')";

if (mysqli_query($conexao, $sql)) {
    echo "Dados inseridos com sucesso!";
} else {
    echo "Erro ao inserir dados: " . mysqli_error($conexao);
}
echo "<a href='manutencao.php'>[Volta para a página principal]</a>";
// Fecha a conexão com o banco de dados
mysqli_close($conexao);
?>