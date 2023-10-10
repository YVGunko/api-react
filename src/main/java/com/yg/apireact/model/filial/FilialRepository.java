package com.yg.apireact.model.filial;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.yg.apireact.model.customer.Customer;

public interface FilialRepository extends PagingAndSortingRepository<Filial,Long>{

	Optional<Filial> findByName(String filial);

}
