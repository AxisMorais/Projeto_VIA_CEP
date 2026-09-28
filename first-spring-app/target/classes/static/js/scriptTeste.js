let cepEncontrado = null;

function buscarCEP() {
    let campoCep = document.getElementById('cep').value;
    campoCep = campoCep.replace(/\D/g, ''); // Limpa o CEP
    //Remove todos os caracteres que NÃO são números da string
	//  \D >> Significa "tudo que NÃO é dígito (número)"
	// \g Significa "global" — procura em toda a string, não só na primeira ocorrência
	//ou seja: pega uma string assim: "30870-200", percorre toda e devolve assim: "30870200"
   

    const url = 'http://localhost:8080/api/cep/' + campoCep;

    // Usando Fetch API para consistência
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error("Erro na requisição: " + response.status);
            }
            return response.json();
        })
        .then(obj => {
            // GUARDA O OBJETO NA VARIÁVEL GLOBAL
            cepEncontrado = obj; 
            
            // Exibe o JSON bruto (opcional, do seu código original)
           // document.getElementById('texto').innerHTML = JSON.stringify(obj);

            // Exibe os dados formatados na tela
            document.getElementById('dados').innerHTML = 
                "Rua: " + obj.logradouro + "<BR>" +
                "Cidade: " + obj.localidade + "<BR>" +
                "Bairro: " + obj.bairro;
        })
        .catch(error => {
            console.error("Erro capturado:", error);
            document.getElementById('dados').innerHTML = "Erro ao buscar o CEP. Verifique o console (F12).";
        });
}

function salvarCep() {
    if (!cepEncontrado) {
        alert("Primeiro busque um CEP!");
        return;
    }
    fetch('http://localhost:8080/api/cep', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(cepEncontrado)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Erro ao salvar: " + response.status);
        }
        return response.json();
    })
    .then(data => {
        alert("CEP salvo com sucesso! ID: " + data.idCep);
    })
    .catch(error => {
        console.error('Erro:', error);
        alert("Erro ao salvar o CEP.");
    });
}

function listarTodosCeps() {
    const divLista = document.getElementById('listaCeps');
    divLista.innerHTML = "Carregando..."; // Mensagem de feedback

    fetch('http://localhost:8080/api/cep/listar')  // ✅ URL CORRETA
        .then(response => {
            if (!response.ok) {
                throw new Error("Erro na requisição: " + response.status);
            }
            return response.json();
        })
        .then(lista => {
            // Se a lista estiver vazia
            if (lista.length === 0) {
                divLista.innerHTML = "<p>Nenhum CEP cadastrado ainda.</p>";
                return;
            }

            // Monta a tabela HTML
            let html = "<table border='1' style='color: white; margin: 20px auto; border-collapse: collapse; width: 90%;'>";
            html += "<tr style='background-color: rgba(0,0,0,0.3);'>";
            html += "<th>ID</th><th>CEP</th><th>Rua</th><th>Cidade</th><th>Bairro</th></tr>";
            
            // Percorre a lista e cria uma linha para cada CEP
            lista.forEach(item => {
                html += `<tr>
                            <td style='padding: 8px; text-align: center;'>${item.idCep}</td>
                            <td style='padding: 8px; text-align: center;'>${item.cep}</td>
                            <td style='padding: 8px;'>${item.logradouro}</td>
                            <td style='padding: 8px;'>${item.localidade}</td>
                            <td style='padding: 8px;'>${item.bairro}</td>
                         </tr>`;
            });
            
            html += "</table>";
            divLista.innerHTML = html;
        })
        .catch(error => {
            console.error("Erro ao listar:", error);
            divLista.innerHTML = "<p style='color: red;'>Erro ao listar os CEPs. Verifique o console (F12).</p>";
        });
}