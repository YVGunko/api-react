package com.yg.apireact.model.color;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ColorRepository extends PagingAndSortingRepository<Color, String> {
	
	Optional<List<Color>> findByDivisionCodeOrderByName(String division_code);

	Optional<List<Color>> findByDivisionCodeAndNameContainingOrderByName(String divisionCode, String color_name);
}
