const contatosList = document.getElementById('contatos-list');
const adicionarContatoForm = document.getElementById('adicionar-contato-form');
const nomeInput = document.getElementById('nome');
const emailInput = document.getElementById('email');
const telefoneInput = document.getElementById('telefone');

// Função para carregar a lista de contatos
function carregarContatos() {
    fetch('http://localhost:8080/contatos')
        .then(response => response.json())
        .then(contatos => {
            contatosList.innerHTML = '';
            contatos.forEach(contato => {
                const li = document.createElement('li');
                li.innerText = `${contato.nome} - ${contato.email} - ${contato.telefone || ''}`;
                contatosList.appendChild(li);
            });
        })
        .catch(error => console.error('Erro ao carregar contatos:', error));
}

// Evento para adicionar um novo contato
adicionarContatoForm.addEventListener('submit', function(event) {
    event.preventDefault();

    const novoContato = {
        nome: nomeInput.value,
        email: emailInput.value,
        telefone: telefoneInput.value
    };

    fetch('http://localhost:8080/contatos', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(novoContato)
    })
    .then(response => response.json())
    .then(contatoAdicionado => {
        console.log('Contato adicionado:', contatoAdicionado);
        carregarContatos();
        nomeInput.value = '';
        emailInput.value = '';
        telefoneInput.value = '';
    })
    .catch(error => console.error('Erro ao adicionar contato:', error));
});

// Carregar a lista de contatos ao carregar a página
carregarContatos();