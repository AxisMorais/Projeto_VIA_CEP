package com.kipper.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;  // ← ADICIONE ESTA LINHA!
import org.springframework.web.bind.annotation.ResponseBody;

import com.kipper.model.Cep;
import com.kipper.service.CepService;

@Controller
public class Rotas {

  
	
   //Página de Boas Vindas - Apenas um tesde de demostração
	@GetMapping("/TesteUm")
    public String TesteUm() {
        return "index";
    }
    
    //Aponta para a Página CEP para realizar as consultas de CEP
    @GetMapping("/TesteDois")
    public String TesteDois() {
        return "PaginaCEP";
    }

  
}