// Função para enviar os dados do formulário para a API em JSON
function enviarFormulario(event) {
    event.preventDefault(); // Evita o comportamento padrão de envio do formulário

    // Obter os dados do formulário
    const nome = document.getElementById('nome').value;
    const telefone = document.getElementById('telefone').value;
    const email = document.getElementById('email').value;
    const cpf = document.getElementById('cpf').value;

    // Montar o objeto JSON com os dados do formulário
    const formData = {
        nome: nome,
        telefone: telefone,
        email: email,
        cpf: cpf,
    };

    // Limpa campos
    document.getElementById('nome').value = "";
    document.getElementById('telefone').value = "";
    document.getElementById('email').value = "";                
    document.getElementById('cpf').value = "";
    
    // Altera o botão enviar
    document.getElementById('submit-button').value = "submetido";
    document.getElementById('submit-button').disable = true;
    
    // Fazer a requisição HTTP para a API usando fetch()
    fetch('URL_DA_API', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
    .then(response => response.json())
    .then(data => {
        console.log('Resposta da API:', data);
        // Aqui você pode tratar a resposta da API, se necessário
    })
    .catch(error => {
        console.error('Erro ao enviar os dados:', error);
    });
}

// Adicionar um ouvinte de evento para o envio do formulário
const form = document.getElementById('formulario');
form.addEventListener('submit', enviarFormulario);