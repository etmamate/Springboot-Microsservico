let token = '';

$(document).ready(function () {
    // Login
    $('#login-form').submit(function (event) {
        event.preventDefault();

        const username = $('#username').val();
        const password = $('#password').val();
        token = 'Basic ' + btoa(`${username}:${password}`);

        // Testar autenticação
        fetch('/api/produtos', {
            method: 'GET',
            headers: { 'Authorization': token }
        })
            .then(response => {
                if (response.ok) {
                    alert('Login bem-sucedido!');
                    $('#produtos-section').show();
                    $('#login-form').hide();
                    carregarProdutos();
                } else {
                    alert('Usuário ou senha incorretos.');
                }
            })
            .catch(error => console.error('Erro:', error));
    });

    // Carregar produtos
    function carregarProdutos() {
        fetch('/api/produtos', {
            headers: { 'Authorization': token }
        })
            .then(response => response.json())
            .then(produtos => {
                $('#lista-produtos').empty();
                produtos.forEach(produto => {
                    $('#lista-produtos').append(`
                            <li id="produto-${produto.id}">
                                <span>${produto.nome} - ${produto.preco} - ${produto.quantidade}</span>
                                <button class="btn-editar" data-id="${produto.id}">Editar</button>
                                <button class="btn-excluir" data-id="${produto.id}">Excluir</button>
                            </li>
                        `);
                });
                // Registrar eventos após os elementos serem carregados
                $('.btn-editar').click(function () {
                    const id = $(this).data('id');
                    editarProduto(id);
                });

                $('.btn-excluir').click(function () {
                    const id = $(this).data('id');
                    excluirProduto(id);
                });
            })
            .catch(error => console.error('Erro ao carregar produtos:', error));
    }

    //Função de editar
    function editarProduto(id) {
        const novoNome = prompt("Digite o novo nome:");
        const novoPreco = prompt("Digite o novo preço:");
        const novaQuantidade = prompt("Digite a nova quantidade:");
        const novaDescricao = prompt("Digite a nova descrição:");

        const produtoAtualizado = {
            nome: novoNome,
            preco: parseFloat(novoPreco),
            quantidade: parseInt(novaQuantidade),
            descricao: novaDescricao
        };

        fetch(`/api/produtos/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            },
            body: JSON.stringify(produtoAtualizado)
        })
            .then(response => {
                if (response.ok) {
                    alert('Produto atualizado com sucesso!');
                    carregarProdutos();
                } else {
                    alert('Erro ao atualizar produto.');
                }
            })
            .catch(error => console.error('Erro ao atualizar produto:', error));
    }

    // Função para excluir produto
    function excluirProduto(id) {
        if (confirm("Tem certeza de que deseja excluir este produto?")) {
            fetch(`/api/produtos/${id}`, {
                method: 'DELETE',
                headers: { 'Authorization': token }
            })
                .then(response => {
                    if (response.ok) {
                        alert('Produto excluído com sucesso!');
                        $(`#produto-${id}`).remove();
                    } else {
                        alert('Erro ao excluir produto.');
                    }
                })
                .catch(error => console.error('Erro ao excluir produto:', error));
        }
    }


    // Adicionar produto
    $('#produto-form').submit(function (event) {
        event.preventDefault();

        const produto = {
            nome: $('#nome').val(),
            preco: $('#preco').val(),
            quantidade: $('#quantidade').val(),
            descricao: $('#descricao').val()
        };

        fetch('/api/produtos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            },
            body: JSON.stringify(produto)
        })
            .then(response => {
                if (response.ok) {
                    alert('Produto adicionado com sucesso!');
                    carregarProdutos();
                    $('#produto-form')[0].reset();
                } else {
                    alert('Erro ao adicionar produto.');
                }
            })
            .catch(error => console.error('Erro:', error));
    });

});