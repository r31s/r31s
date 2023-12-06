<?php
require 'valida_login.php';
?>

<!DOCTYPE html>
<html>
    <head>
        <title>.</title>
    </head>
    <body>
        <h1>Bem-vindo(a), <?php echo $_SESSION['usuario']['nome']; ?>!</h1>
        <?php if ($_SESSION['usuario']['funcao'] == 'administrador'): ?>
            <p>Você é um(a) administrador(a) e pode realizar todas as funções do sistema.</p>
        <?php elseif ($_SESSION['usuario']['funcao'] == 'usuario'): ?>
            <p>Você é um(a) usuário(a) e pode realizar somente algumas funções do sistema.</p>
        <?php endif; ?>
        <p><a href="logout.php">Sair</a></p>
        <p><a href="manutencao.php">lista de manutencao</a></p>
    </body>
</html>