package com.kipper.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kipper.model.Cep;
import com.kipper.service.CepService;

@RestController
@RequestMapping("/api/cep")
public class CepController {

    private final CepService cepService;

    // Injeção de dependência: O Spring nos entrega o CepService pronto
    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    // 1. Endpoint para BUSCAR um CEP específico (chamado pelo botão BUSCAR)
    @GetMapping("/{cep}")
    public Cep buscarCep(@PathVariable String cep) {
        return cepService.buscar_Cep(cep);
    }

    // 2. Endpoint para LISTAR todos os CEPs salvos (chamado pelo botão Listar)
    @GetMapping("/listar")
    public List<Cep> listarTodos() {
        return cepService.listarTodos();
    }

    // 3. Endpoint para SALVAR um CEP (chamado pelo botão Salvar)
    @PostMapping
    public Cep salvarCep(@RequestBody Cep cep) {
        return cepService.salvarCep(cep);
    }
}