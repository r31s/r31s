<?php
require 'valida_login.php';
?>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="utf-8">
    <title>Formulário - Cadastro de cliente</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>
    <h2>Preencha o formulário:</h2>
    <form action="inclui_cliente.php" method="post">

        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome"><br><br>

        <label for="telefone">telefone:</label>
        <input type="text" id="telefone" name="telefone"><br><br>

        <label for="email">email</label>
        <input type="text" id="email" name="email"><br><br>

        <input type="submit" value="Enviar"><br><br>
    </form>
</body>
</html>