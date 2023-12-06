<?php
session_start();

if (isset($_SESSION['usuario'])) {
    header('Location: index.php');
    exit;
}

$usuarios = [
    [
        'nome' => 'Administrador',
        'email' => 'admin@zemail.com',
        'senha' => 'admin',
        'funcao' => 'administrador',
    ],
    [
        'nome' => 'Usuário',
        'email' => 'user@zemail.com',
        'senha' => 'user',
        'funcao' => 'usuario',
    ],
];

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $email = $_POST['email'];
    $senha = $_POST['senha'];

    foreach ($usuarios as $usuario) {
        if ($usuario['email'] == $email && $usuario['senha'] == $senha) {
            $_SESSION['usuario'] = $usuario;
            header('Location: index.php');
            exit;
        }
    }

    $erro = 'E-mail ou senha inválidos.';
}
?>

<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <?php if (isset($erro)): ?>
            <p><?php echo $erro; ?></p>
        <?php endif; ?>
        <form method="post">
            <div>
                <label for="email">E-mail:</label>
                <input type="email" name="email" required>
            </div>
            <div>
                <label for="senha">Senha:</label>
                <input type="password" name="senha" required>
            </div>
            <div>
                <button type="submit">Entrar</button>
            </div>
        </form>
    </body>
</html>