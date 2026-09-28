package com.kipper.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.kipper.model.Cep;
import com.kipper.repository.CepRepository;


@Service
public class CepService {

	private final CepRepository repository;
	private final RestClient restClient;
	
	//Injeção de dependências o Spring 
	public CepService(CepRepository repository) {
		this.repository =repository;
		this.restClient = RestClient.create();
	}
	
	public Cep buscar_Cep(String cepDigitado) {
	    // 1º Monta a URL do ViaCEP
	    String url = "https://viacep.com.br/ws/" + cepDigitado + "/json/";

	    // 2º Faz a requisição e já converte o JSON para o objeto Cep
	    Cep cep = restClient.get()
	            .uri(url)
	            .retrieve()
	            .body(Cep.class);
	    
	    /* 
		   Função do URI - url linha 28
		   .uri(url)
         O que faz: Define o endereço de destino da requisição 
         ( URI - Uniform Resource Identifier) Na prática: É como você 
         digitar o endereço no GPS ou escrever o destinatário 
         em um envelope. Você está dizendo 
         ao RestClient: "Ei, vá para este endereço específico".
		
		 .retrieve()
        O que faz: É o comando de "Ação!" ou "Executar!". 
        É neste exato momento que o RestClient abre a conexão de rede, 
        envia a requisição para o servidor do ViaCEP 
        e espera a resposta chegar.	
				
		*/

	    if (cep != null) {
	        // 3. Garante que o número do CEP está preenchido no objeto
	        cep.setCep(cepDigitado);
	        return cep; 
	    }

	    // Se o cep for nulo, aí sim lança a exceção
	    throw new RuntimeException("CEP não encontrado ou erro na requisição");
	}
	
	//--------------------------------------
	
	public Cep salvarCep(Cep cep) {
	    return repository.save(cep);
	}
	
	
	//Listar todas as ocorrências cadastradas
	 public List<Cep> listarTodos(){
		 return repository.findAll();
	 }

	
	
}
