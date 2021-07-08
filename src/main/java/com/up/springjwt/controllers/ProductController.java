package com.up.springjwt.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.up.springjwt.models.Product;
import com.up.springjwt.payload.response.MessageResponse;
import com.up.springjwt.security.services.ProductService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping()
	public ResponseEntity<MessageResponse> saveProduct(@Valid @RequestBody Product product) {
		productService.save(product);
		return ResponseEntity.ok(new MessageResponse("Product registered successfully!"));
	}
	
	@GetMapping()
	public ResponseEntity<?> getMany(
			@RequestParam(defaultValue = "1") int page,
	        @RequestParam(defaultValue = "50") int limit
	) {
		try {
			Page<Product> pageProduct;
			page = page == 0? page : page-1;
			Pageable paging = PageRequest.of(page, limit);
			pageProduct = productService.findAll(paging);
			return new ResponseEntity<>(pageProduct, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable ( value = "id") long id) {
		Product product = productService.getProuctById(id);
		return ResponseEntity.ok(product);
	}
	
	@DeleteMapping({"/{id}"})
	public ResponseEntity<MessageResponse> deleteProduct(@PathVariable (value = "id") long id) {
		boolean isDelete = productService.delete(id);
		if(isDelete) {
			return ResponseEntity.ok(new MessageResponse("Product deleted successfully!"));
		} else {
			return ResponseEntity.ok(new MessageResponse("Product not found"));
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MessageResponse> updateOne(@PathVariable("id") long id, @RequestBody Product product) {
		Optional<Product> data  = Optional.of(productService.getProuctById(id));
		if(data.isPresent()) {
			productService.save(product);
			return ResponseEntity.ok(new MessageResponse("Update product success!!"));
		} else {
			return ResponseEntity.ok(new MessageResponse("Product not found"));
		}
	}
}
