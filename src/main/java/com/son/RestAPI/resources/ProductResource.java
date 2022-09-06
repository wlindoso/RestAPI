package com.son.RestAPI.resources;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.son.RestAPI.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductResource {

	@Autowired
	ProductService productService;

	public ProductResource(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	@ResponseBody // caso precisa retornar algo
	public List<Product> findAll() {
		return this.productService.findAll();
	}

	@GetMapping(value = "/{id}") // Recebe na URL, o id do produto a buscar.
	@ResponseBody
	public Optional<Product> findById(@PathVariable(value = "id") Long id) {
		return this.productService.findById(id);
	}

	@PostMapping
	@ResponseBody
	public Product create(@RequestBody Product product) {
		return this.productService.create(product);
	}

	@PutMapping(value = "/{id}") // Recebe na URL, o id do produto a atualizar.
	@ResponseBody
	public Product update(@PathVariable(value = "id") Long id, @RequestBody Product product) {
		return this.productService.update(id, product);
	}

	@DeleteMapping(value = "/{id}") // Recebe na URL, o id do produto a deletar.
	@ResponseBody
	public void delete(@PathVariable(value = "id") Long id, HttpServletResponse response) {
		this.productService.delete(id);
		response.setStatus(HttpServletResponse.SC_NO_CONTENT); // Code 204
	}

}
