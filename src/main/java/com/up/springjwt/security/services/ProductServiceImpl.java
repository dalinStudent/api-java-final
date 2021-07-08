package com.up.springjwt.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.up.springjwt.models.Product;
import com.up.springjwt.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void save(Product product) {
		this.productRepository.save(product);
	}

	@Override
	public Boolean delete(long id) {
		Optional<Product> optional = productRepository.findById(id);
		boolean isDelete = true;
		if(optional.isPresent()) {
			this.productRepository.deleteById(id);
		} else {
			isDelete = false;
		}
		return isDelete;
	}

	@Override
	public Page<Product> findAll(Pageable paging) {
		return this.productRepository.findAll(paging);
	}

	@Override
	public Product getProuctById(long id) {
		Optional<Product> optional = this.productRepository.findById(id);
		Product product = null;
		if (optional.isPresent()) {
			product = optional.get();
		} else {
			throw new RuntimeException("Product not found for id");
		}
		return product;
	}

}
