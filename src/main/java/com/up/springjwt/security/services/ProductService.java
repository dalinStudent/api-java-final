package com.up.springjwt.security.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.up.springjwt.models.Product;

public interface ProductService {
	void save(Product product);
	Product getProuctById(long id);
	Boolean delete(long id);
	Page<Product> findAll(Pageable paging);
}
