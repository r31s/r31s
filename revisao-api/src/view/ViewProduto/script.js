const produtosList = document.getElementById('produtos-list');

// Função para carregar a lista de produtos
function carregarProdutos() {
    fetch('http://localhost:8080/produtos')
        .then(response => response.json())
        .then(produtos => {
            produtosList.innerHTML = '';
            produtos.forEach(produto => {
                const produtoCard = criarProdutoCard(produto);
                produtosList.appendChild(produtoCard);
            });
        })
        .catch(error => console.error('Erro ao carregar produtos:', error));
}

// Função para criar um card para um produto
function criarProdutoCard(produto) {
    const produtoCard = document.createElement('div');
    produtoCard.className = 'produto-card';

    const imagem = document.createElement('img');
    imagem.src = produto.imagemUrl; // Adicione a URL da imagem do produto se houver
    imagem.alt = produto.nome;

    const nome = document.createElement('h3');
    nome.innerText = produto.nome;

    const descricao = document.createElement('p');
    descricao.innerText = produto.descricao;

    const preco = document.createElement('p');
    preco.innerText = `Preço: R$ ${produto.preco.toFixed(2)}`;

    const botaoComprar = document.createElement('button');
    botaoComprar.innerText = 'Comprar';
    botaoComprar.addEventListener('click', () => {
        alert(`Você comprou o produto: ${produto.nome}`);
    });

    produtoCard.appendChild(imagem);
    produtoCard.appendChild(nome);
    produtoCard.appendChild(descricao);
    produtoCard.appendChild(preco);
    produtoCard.appendChild(botaoComprar);

    return produtoCard;
}

// Carregar a lista de produtos ao carregar a página
carregarProdutos();
