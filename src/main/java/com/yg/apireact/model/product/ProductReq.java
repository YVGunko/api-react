package com.yg.apireact.model.product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductReq {
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductReq(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public ProductReq() {
		super();
	}

	private String id;
	private String name;

 	public static ProductReq productToProductReq (Product b) {
 		return new ProductReq(b.getId(), b.getName());		
 	}
 	public static List<ProductReq> productToProductReq (List<Product> list) {
 		return list.stream().map(b -> productToProductReq(b)).collect(Collectors.toList());		
 	}
}
