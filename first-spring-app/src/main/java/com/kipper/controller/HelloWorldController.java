package com.kipper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class HelloWorldController {

    @GetMapping
    public String olaMundo() {
        return "<Strong> Teste Inicial configurado com sucesso! </Strong>";
    }
    
  
  
}