package com.yg.apireact.model.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, String> {

	Optional<List<Product>> findTop100ByDivisionCodeAndNameContainingOrderByName(String division_code,
			String product_name);

	Optional<List<Product>> findTop100ByDivisionCodeOrderByName(String division_code);

	Optional<List<Product>> findByDivisionCodeAndNameContainingOrderByName(String division_code, String product_name);

	Optional<List<Product>> findByDivisionCodeOrderByName(String divisionCode);
}
