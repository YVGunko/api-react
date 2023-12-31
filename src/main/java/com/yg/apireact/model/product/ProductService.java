package com.yg.apireact.model.product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yg.apireact.model.division.DivisionRepository;

@Service
public class ProductService {
	private static final Logger log = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	ProductRepository repo;
	@Autowired
	DivisionRepository repoDiv;

	ResponseEntity<List<ProductReq>> find(String divisionCode, String productName) {
		List<ProductReq> respond = new ArrayList<ProductReq>();
		try {
			Assert.hasLength(divisionCode, "Division Code provided isn't valid");
			repoDiv.findById(divisionCode).orElseThrow(() -> new NoSuchElementException(
					"Division not found exception. divisionCode=".concat(divisionCode)));

			if (StringUtils.isNotBlank(productName)) {
				respond = ProductReq.productToProductReq(repo.findByDivisionCodeAndNameContainingOrderByName(divisionCode, productName)
						.orElseGet(() -> List.of(new Product())));
			} else {
				respond = ProductReq.productToProductReq(repo.findByDivisionCodeOrderByName(divisionCode)
						.orElseGet(() -> List.of(new Product())));
			}
			return new ResponseEntity<>(respond, HttpStatus.OK);
		} catch (IllegalArgumentException | NoSuchElementException e) {
			log.error("Product not found exception. divisionCode=".concat(divisionCode).concat("productName = ")
					.concat(productName));
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.error("Product find method's been raised Exception. Message=", e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	ResponseEntity<ProductReq> find(String id) {
		try {
			Assert.hasLength(id, "id provided isn't valid");

			ProductReq respond = ProductReq.productToProductReq(repo.findById(id).orElseThrow());

			return new ResponseEntity<>(respond, HttpStatus.OK);
		} catch (IllegalArgumentException | NoSuchElementException e) {
			log.error("Product not found exception. id=".concat(id));
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.error("Product find method's been raised Exception. Message=", e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
