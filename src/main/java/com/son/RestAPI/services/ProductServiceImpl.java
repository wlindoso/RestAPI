package com.son.RestAPI.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.son.RestAPI.models.Product;
import com.son.RestAPI.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired // Dependecy Injection
	private ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> findAll() {
		return this.productRepository.findAll();
	}

	@Override
	public Optional<Product> findById(Long id) {
		Optional<Product> product = this.productRepository.findById(id);
		return product;
	}

	@Override
	public Product create(Product product) {
		this.productRepository.save(product);
		return product;
	}

	@Override
	public Product update(Long id, Product product) {
		Optional<Product> productExists = this.productRepository.findById(id);
		if (productExists != null) {
			product.setId(productExists.get().getId());
			this.productRepository.save(product);
			return product;
		}
		return null;
	}

	@Override
	public void delete(Long id) {
		Optional<Product> product = this.productRepository.findById(id);
		if (product != null) {
			this.productRepository.delete(product.get());
		}
	}

}
