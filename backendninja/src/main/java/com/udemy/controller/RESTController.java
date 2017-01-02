package com.udemy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.model.ContactModel;

@RestController
@RequestMapping("/rest")
public class RESTController {

	@GetMapping("/checkrest")
	//Possui a mesma logica dos controladores normais, com a diferenca que o retorno eh do tipo ResponseEntity
	//para que um determinado conteudo seja devolvido em formato JSON
	public ResponseEntity<ContactModel> checkREST() {
		
		ContactModel cm = new ContactModel(2, "Jefferson", "Araujo", "+55 11 966861987", "São Paulo");
		
		return new ResponseEntity<>(cm, HttpStatus.OK);
	}
}
