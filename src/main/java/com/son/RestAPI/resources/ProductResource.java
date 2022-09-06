package com.son.RestAPI.resources;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.son.RestAPI.models.Product;

@RestController
@RequestMapping("/products")
public class ProductResource {

	@GetMapping
	@ResponseBody // caso precisa retornar algo
	public List<Product> findAll() {
		return null;
	}

	@GetMapping(value = "/{id}") // Recebe na URL, o id do produto a buscar.
	@ResponseBody
	public Product find(@PathVariable(value = "id") Long id) {
		return null;
	}

	@PostMapping
	@ResponseBody
	public Product create(@RequestBody Product product) {
		return null;
	}

	@PutMapping(value = "/{id}") // Recebe na URL, o id do produto a atualizar.
	@ResponseBody
	public Product update(@PathVariable(value = "id") Long id, @RequestBody Product product) {
		return null;
	}

	@DeleteMapping(value = "/{id}") // Recebe na URL, o id do produto a deletar.
	@ResponseBody
	public void delete(@PathVariable(value = "id") Long id) {

	}

}
