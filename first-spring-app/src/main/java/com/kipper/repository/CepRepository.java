package com.kipper.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kipper.model.Cep;


@Repository
public interface CepRepository extends JpaRepository<Cep, Integer> {
	
	 // O Spring Boot vai implementar automaticamente os métodos de salvar, buscar, etc.
    // O "Cep" é a entidade e o "Integer" é o tipo do ID (idCep).
	
	

}
