package com.up.springjwt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	@Column(name = "name")
	private String name;
	
	@NotBlank
	@Column(name = "quanity")
	private String quanity;
	
	@NotBlank
	@Column(name = "price")
	private String price;
	
	public Product() {
		
	}
	
	public Product(long id, @NotBlank String name, @NotBlank String quanity, @NotBlank String price) {
		super();
		this.id = id;
		this.name = name;
		this.quanity = quanity;
		this.price = price;
	}
	
	public Product(@NotBlank String name, @NotBlank String quanity, @NotBlank String price) {
		super();
		this.name = name;
		this.quanity = quanity;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuanity() {
		return quanity;
	}

	public void setQuanity(String quanity) {
		this.quanity = quanity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}

