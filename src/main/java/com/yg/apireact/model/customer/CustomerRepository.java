package com.yg.apireact.model.customer;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface CustomerRepository extends PagingAndSortingRepository<Customer, String> {// <1>
	//@RestResource(rel = "name-contains", path="name-contains", exported = false)
	Page<Customer> findByNameContainingOrderByName(String name, Pageable pageable);
	Optional<Customer> findById(String id);
	Page<Customer> findAllByOrderByName(Pageable paging);

}
