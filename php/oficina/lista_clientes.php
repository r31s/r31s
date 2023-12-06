<?php
require 'valida_login.php';
?>

<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<title>Lista de Clientes</title>
	</head>
	<body>
		<!-- Título da página -->
		<h2>Lista de Clientes</h2>
		<!-- Tabela de exibição dos clientes -->
		<table>
			<!-- Cabeçalho da tabela -->
			<thead>
				<tr>
					<th>Código</th>
					<th>Nome</th>
					<th>Telefone</th>
					<th>e-mail</th>
                    <th>Ações</th> 
				</tr>
			</thead>
			<!-- Corpo da tabela -->
			<tbody>
				<?php
				require 'conecta_banco.php'; // conecta com o banco de dados :)

				// Consulta na tabela de tb_clientes
				$sql = "SELECT * FROM tb_clientes";
				$resultado = mysqli_query($conexao, $sql);

				// Loop para exibir os resultados
				while ($cliente = mysqli_fetch_array($resultado)) {
					echo "<tr>";
					echo "<td>" . $cliente['codigo'] . "</td>";
					echo "<td>" . $cliente['nome'] . "</td>";
					echo "<td>" . $cliente['telefone'] . "</td>";
					echo "<td>" . $cliente['email'] . "</td>";
                    // Adiciona link para edição da peça
                    echo "<td><a href='edita_cliente.php?codigo=" . $cliente['codigo'] . "'>Editar</a></td>"; 
					echo "<td><a href='exclui_cliente.php?codigo=" . $cliente['codigo'] . "'>Exclui</a></td>"; 
					echo "</tr>";
				}

				// Fechar a conexão com o banco de dados
				mysqli_close($conexao);
				?>
			</tbody>
		</table>
	<a href="manutencao.php">[Volta para a página principal]</a>
	</body>
</html>     