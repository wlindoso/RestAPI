package com.son.RestAPI.resources;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.son.RestAPI.models.Product;
import com.son.RestAPI.services.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "API REST - Model Product")
@RestController
@RequestMapping("/products")
public class ProductResource {

	@Autowired
	ProductService productService;

	public ProductResource(ProductService productService) {
		this.productService = productService;
	}

	@ApiOperation(value = "Find all products in database.")
	@GetMapping(produces = "application/json")
	@ResponseBody // caso precisa retornar algo
	public ResponseEntity<?> findAll() {
		List<Product> list = this.productService.findAll();
		return new ResponseEntity<List>(list, HttpStatus.OK);
	}

	@ApiOperation(value = "Find by ID in database.")
	@GetMapping(value = "/{id}") // Recebe na URL, o id do produto a buscar.
	@ResponseBody
	public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
		Optional<Product> product = this.productService.findById(id);
		return new ResponseEntity<Optional<Product>>(product, HttpStatus.OK);
	}

	@ApiOperation(value = "Create new product in database.")
	@PostMapping
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED) // mais utilizado dentro do Spring // CREATED - 201
	public ResponseEntity<?> create(@Valid @RequestBody Product product, Errors errors) {
		if (!errors.hasErrors()) {
			Product productCreated = this.productService.create(product);
//			return ResponseEntity.ok(productCreated);
			return new ResponseEntity<Product>(productCreated, HttpStatus.CREATED);
		}
		return ResponseEntity.badRequest().body(
				errors.getAllErrors().stream().map(msg -> msg.getDefaultMessage()).collect(Collectors.joining(",")));
	}
//	@PostMapping
//	@ResponseBody
//	@ResponseStatus(code = HttpStatus.CREATED) // mais utilizado dentro do Spring // CREATED - 201
//	public Product create(@Valid @RequestBody Product product) {
//		return this.productService.create(product);
//	}

	@ApiOperation(value = "Update product in database.")
	@PutMapping(value = "/{id}") // Recebe na URL, o id do produto a atualizar.
	@ResponseBody
	public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Product product,
			Errors errors) {
		if (!errors.hasErrors()) {
			Product productUpdated = this.productService.update(id, product);
			return new ResponseEntity<Product>(productUpdated, HttpStatus.OK);
		}
		return ResponseEntity.badRequest().body(
				errors.getAllErrors().stream().map(msg -> msg.getDefaultMessage()).collect(Collectors.joining(",")));
	}

	@ApiOperation(value = "Delete product in database.")
	@DeleteMapping(value = "/{id}") // Recebe na URL, o id do produto a deletar.
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(value = "id") Long id) {
		this.productService.delete(id);
	}

}
