package com.yg.apireact.model.customer;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface CustomerRepository extends PagingAndSortingRepository<Customer, String> {// <1>
	//@RestResource(rel = "name-contains", path="name-contains", exported = false)
	Page<Customer> findByNameContaining(String name, Pageable pageable);
	List<Customer> findByid(String id);
}
